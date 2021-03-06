package com.zsmartsystems.zwave.transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zwave.ZWaveCommandClassPayload;
import com.zsmartsystems.zwave.ZWaveNetwork;
import com.zsmartsystems.zwave.ZWaveNode;
import com.zsmartsystems.zwave.commandclass.ZWaveCommandClassEnum;
import com.zsmartsystems.zwave.commandclass.ZWaveSecurityCommandClass;
import com.zsmartsystems.zwave.transaction.ZWaveTransaction.TransactionState;
import com.zsmartsystems.zwave.transaction.ZWaveTransactionResponse.State;

/**
 * This class handles transactions for the Z-Wave controller. A transaction contains a set of messages required
 * to transfer data to a device, and potentially receive data back.
 * <p>
 * There can be a number of different transaction types -:
 * <ul>
 * <li><i>REQ</i>uest from openHAB, no response other than the low level <i>ACK</i></li>
 * <li><i>REQ</i>uest from openHAB, <i>RES</i>ponse from controller</li>
 * <li><i>REQ</i>uest from openHAB, <i>REQ</i>uest from controller</li>
 * <li><i>REQ</i>uest from openHAB, <i>RES</i>ponse from controller, <i>REQ</i>uest from controller</li>
 * <li><i>REQ</i>uest from openHAB, <i>RES</i>ponse from controller, <i>REQ</i>uest from controller,
 * <i>DATA</i> from node</li>
 * </ul>
 * The responses required for each transaction is defined in the {@link SerialMessage} class and these are
 * tracked in this class.
 * </p>
 * <p>
 * Most transactions are SendData messages which request data from a device. These are the last type of transaction.
 * The meaning of each step is as follows -:
 * <ul>
 * <li><i>REQ</i>uest sent from openHAB sends the command class data.</li>
 * <li><i>RES</i>ponse from the controller advises that the controller accepts the message and it will be sent to the
 * device.
 * At this time, transactions to other nodes <u>could</u> be sent. Transactions are tracked using a callback ID,</li>
 * <li><i>REQ</i>uest from the controller advises that the device has received the message (or the message has timed
 * out).</li>
 * <li><i>DATA</i> message from the device is received.</li>
 * </ul>
 * This definition allows multiple messages to be outstanding at once. From a Z-Wave API perspective, the <i>DATA</i>
 * response
 * is outside of a controllers transaction, so we are allowed to have multiple transactions. However, we don't want to
 * overload a device, so we need to restrict requests to any node to ensure only a single transaction is outstanding at
 * once.
 * </p>
 * <p>
 * The following transaction management 'rules' are applied -:
 * <ul>
 * <li>Only a single transaction still awaiting a <i>RES</i>ponse can be outstanding to ANY node.</li>
 * <li>Only a single transaction still awaiting a <i>REQ</i>uest can be outstanding to a specific node.</li>
 * <li>Only a single transaction requiring a <i>DATA</i> response can be released at once to a specific node.</li>
 * <li>A total of MAX_OUTSTANDING_TRANSACTIONS can be outstanding at once.</li>
 * </ul>
 * </p>
 * <h2>Transaction Flow</h2>
 * <p>
 * Transactions are processed as follows -:
 * <ul>
 * <li>{@link ZWaveTransactions} containing the {@link SerialMessage} are added to the transmit queue with
 * {@link #queueTransactionForSend} method.</li>
 * <li>If a transaction can be sent, then it is transmitted. The sent transaction is added to the sent
 * transaction list so that received messages can be correlated with the transaction and timeouts can be managed.</li>
 * <li></li>
 * <li></li>
 * <li></li>
 * </ul>
 * </p>
 * <h2>TX Message Queue</h2>
 * <p>
 * Messages are queued in this class. This allows transmit messages to be released with knowledge of
 * other transactions that are outstanding. Specifically, we only want to release a single transaction
 * per node at once (with the exception below).
 * </p>
 * <p>
 * This class provides a semaphore to block the send thread (or maybe this should directly call the send method?)
 * </p>
 * <p>
 * Only a single transaction requiring a response can be released at once to any single node. This
 * specifically allows NONCE responses to be sent in the middle of a secure transaction.
 * <p>
 * <h2>Timeouts</h2>
 * <p>
 * A timer thread manages timeouts - different times are used for the different stages of a transaction.
 * Defaults for each timer are as follows -:
 * <ul>
 * <li><i>RES</i>ponse - should be received within <b>250ms</b> of the <i>REQ</i>uest</li>
 * <li><i>REQ</i>uest - should be received within <b>2500ms</b> of the <i>REQ</i>uest or the controllers <i>RES</i>ponse
 * </li>
 * <li><i>DATA</i> - should be received within <b>2500ms</b> of the <i>REQ</i>uest.</li>
 * </ul>
 * When a timeout occurs - what????
 * </p>
 * <h2>Cancelled Transactions</h2>
 * <p>
 * Transactions can be cancelled by a message handler - for example if there's a timeout in a transaction we can
 * cancel the transaction without waiting for the DATA response.
 * </p>
 *
 * @author Chris Jackson - Initial Contribution
 *
 */
public class ZWaveTransactionManager {
    private Logger logger = LoggerFactory.getLogger(ZWaveTransactionManager.class);

    private final int INITIAL_TX_QUEUE_SIZE = 128;
    // private final int MAX_OUTSTANDING_TRANSACTIONS = 3;

    private ZWaveNetwork controller;

    /**
     * Timeout in which we expect the initial response from the controller
     */
    private final long timer1 = 2000;

    /**
     * Timeout in which we expect the request from the controller
     */
    private final long timer2 = 5000;

    /**
     * Timeout waiting to cancel a transaction once we've aborted it
     */
    private final long timerAbort = 12000;

    private final Timer timer = new Timer();
    private TimerTask timerTask = null;

    private final ArrayBlockingQueue<ZWaveCommandClassPayload> recvQueue;

    private final PriorityBlockingQueue<ZWaveTransaction> sendQueue = new PriorityBlockingQueue<ZWaveTransaction>(
            INITIAL_TX_QUEUE_SIZE, new ZWaveTransactionComparator());
    private final PriorityBlockingQueue<ZWaveTransaction> secureQueue = new PriorityBlockingQueue<ZWaveTransaction>(
            INITIAL_TX_QUEUE_SIZE, new ZWaveTransactionComparator());
    private final PriorityBlockingQueue<ZWaveTransaction> controllerQueue = new PriorityBlockingQueue<ZWaveTransaction>(
            INITIAL_TX_QUEUE_SIZE, new ZWaveTransactionComparator());

    private ZWaveReceiveThread receiveThread;

    private ExecutorService executor = Executors.newCachedThreadPool();
    private final List<TransactionListener> transactionListeners = new ArrayList<TransactionListener>();

    private final List<ZWaveTransaction> outstandingTransactions = new ArrayList<ZWaveTransaction>();

    private ZWaveTransaction lastTransaction = null;

    public ZWaveTransactionManager(ZWaveNetwork controller) {
        this.controller = controller;

        recvQueue = new ArrayBlockingQueue<ZWaveCommandClassPayload>(INITIAL_TX_QUEUE_SIZE);

        receiveThread = new ZWaveReceiveThread();
        receiveThread.start();
    }

    private void addTransactionListener(TransactionListener listener) {
        synchronized (transactionListeners) {
            if (transactionListeners.contains(listener)) {
                return;
            }

            transactionListeners.add(listener);
        }
    }

    private void removeTransactionListener(TransactionListener listener) {
        synchronized (transactionListeners) {
            transactionListeners.remove(listener);
        }
    }

    private void notifyTransactionComplete(final ZWaveTransaction transaction) {
        logger.debug("NODE {}: notifyTransactionResponse {}", transaction.getNodeId(), transaction.getTransactionId());
        new Thread() {
            @Override
            public void run() {
                synchronized (transactionListeners) {
                    for (TransactionListener listener : transactionListeners) {
                        listener.transactionEvent(transaction);
                    }
                }
            }
        }.start();

        // If this transaction isn't complete, check if it's a secure transaction as we need to
        // abort the original request.
        if (transaction.getTransactionState() != TransactionState.DONE
                && transaction instanceof ZWaveSecureTransaction) {
            ZWaveSecureTransaction secureTransaction = (ZWaveSecureTransaction) transaction;
            logger.debug("NODE {}: processing secure transaction -- {}", transaction.getNodeId(),
                    secureTransaction.getLinkedTransaction().getTransactionId());

            synchronized (transactionListeners) {
                for (TransactionListener listener : transactionListeners) {
                    listener.transactionEvent(secureTransaction.getLinkedTransaction());
                }
            }

            synchronized (sendQueue) {
                sendQueue.remove(secureTransaction.getLinkedTransaction());
            }
        }
    }

    /**
     * This method takes a {@link ZWaveMessagePayload} and creates a {@link ZWaveTransaction} for a SendData message.
     *
     * @param payload
     * @return
     */
    public long queueNonceReportForSend(ZWaveCommandClassTransactionPayload payload) {
        // Create a transaction from our payload data
        ZWaveTransaction transaction = new ZWaveTransaction(payload);
        if (payload.getMaxAttempts() != 0) {
            transaction.setAttemptsRemaining(payload.getMaxAttempts());
        }
        // transaction.getSerialMessageClass();

        // Add the transaction to the queue
        secureQueue.add(transaction);
        logger.debug("NODE {}: Added to secure queue - size {}", transaction.getNodeId(), secureQueue.size());

        return transaction.getTransactionId();
    }

    public long queueTransactionForSend(ZWaveCommandClassTransactionPayload payload) {

        // Create a transaction from our payload data
        ZWaveTransaction transaction = new ZWaveTransaction(payload);
        if (payload.getMaxAttempts() != 0) {
            transaction.setAttemptsRemaining(payload.getMaxAttempts());
        }
        // transaction.getSerialMessageClass();

        // Add the transaction to the queue
        addTransactionToQueue(transaction);

        return transaction.getTransactionId();
    }

    private void addTransactionToQueue(ZWaveTransaction transaction) {
        synchronized (sendQueue) {
            if (sendQueue.contains(transaction)) {
                logger.debug("NODE {}: Transaction already in queue - removing original", transaction.getNodeId());
                sendQueue.remove(transaction);
            }
            sendQueue.add(transaction);
            logger.debug("NODE {}: Added to queue - size {}", transaction.getNodeId(), controllerQueue.size());
        }

        sendNextMessage();
        startTransactionTimer();
    }

    /**
     * Gets the number of messages currently in the transmit queue for a specific node. This includes transactions that
     * are in the outstanding queue.
     *
     * @return number of messages in queue
     */
    public int getSendQueueLength(int nodeId) {
        if (nodeId == 255) {
            return controllerQueue.size();
        }

        synchronized (sendQueue) {
            int outstandingCount = 0;
            for (ZWaveTransaction transaction : secureQueue) {
                if (transaction.getNodeId() == nodeId) {
                    outstandingCount++;
                }
            }
            for (ZWaveTransaction transaction : sendQueue) {
                if (transaction.getNodeId() == nodeId) {
                    outstandingCount++;
                }
            }
            for (ZWaveTransaction transaction : outstandingTransactions) {
                if (transaction.getNodeId() == nodeId) {
                    outstandingCount++;
                }
            }

            return outstandingCount;
        }
    }

    /**
     * Clear the send queue
     */
    public void clearSendQueue() {
        synchronized (sendQueue) {
            sendQueue.clear();
            secureQueue.clear();
            controllerQueue.clear();
        }
    }

    /**
     * Processes an incoming {@link ZWaveCommandClassPayload}
     * This is called by the receive processing queue.
     *
     * @param incomingMessage
     */
    public void processReceiveMessage(ZWaveCommandClassPayload incomingMessage) {
        logger.debug("processReceiveMessage input" + incomingMessage.toString());

        synchronized (recvQueue) {
            logger.debug("processReceiveMessage past lock" + incomingMessage.toString());
            recvQueue.add(incomingMessage);
            recvQueue.notify();
        }
    }

    private class ZWaveReceiveThread extends Thread {
        @Override
        public void run() {
            ZWaveCommandClassPayload incomingMessage;
            while (!interrupted()) {
                if (recvQueue.isEmpty()) {
                    logger.debug("ZWaveReceiveThread queue empty");

                    // See if we need to send another message
                    sendNextMessage();
                    startTransactionTimer();
                }

                try {
                    incomingMessage = recvQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    continue;
                }

                ZWaveTransaction currentTransaction = null;
                logger.debug("Received msg " + incomingMessage.toString());
                logger.debug("lastTransaction " + lastTransaction);

                // TODO: Get the node from somewhere!!!!!!
                int nodeId = 0;// incomingMessage.getMessagePayloadByte(1);
                ZWaveNode node = controller.getNode(nodeId);

                if (node == null) {
                    logger.warn("NODE {}: Not initialized (ie node unknown), ignoring message.", nodeId);
                } else {
                    // logger.debug("NODE {}: Application Command Request ({}:{})", nodeId,
                    // node.getNodeState().toString(),
                    // node.getNodeInitStage().toString());

                    List<ZWaveCommandClassPayload> commands = node.processCommand(incomingMessage);
                    if (commands != null) {
                        logger.debug("NODE {}: Commands processed {}.", nodeId, commands.size());

                        for (ZWaveCommandClassPayload command : commands) {
                            logger.debug("NODE {}: Checking command {}.", nodeId, command);
                            // Correlate transactions
                            List<ZWaveTransaction> completed = new ArrayList<ZWaveTransaction>();

                            synchronized (sendQueue) {
                                for (ZWaveTransaction transaction : outstandingTransactions) {
                                    logger.debug("NODE {}: Checking transaction : state >> {}", nodeId,
                                            transaction.getTransactionState());
                                    logger.debug("NODE {}: Checking transaction : node  >> {}", nodeId,
                                            transaction.getNodeId());
                                    logger.debug("NODE {}: Checking transaction : class >> {} == {}.", nodeId,
                                            command.getCommandClassId(), transaction.getExpectedCommandClass() == null
                                                    ? null : transaction.getExpectedCommandClass().getKey());
                                    logger.debug("NODE {}: Checking transaction : commd >> {} == {}.", nodeId,
                                            command.getCommandClassCommand(),
                                            transaction.getExpectedCommandClassCommand());

                                    if (transaction.getTransactionState() != TransactionState.WAIT_DATA) {
                                        logger.debug("NODE {}: Ignoring transaction since not waiting for data.",
                                                nodeId);
                                        continue;
                                    }

                                    if (transaction.getExpectedCommandClass() != null
                                            && command.getCommandClassId() == transaction.getExpectedCommandClass()
                                                    .getKey()
                                            && nodeId == transaction.getNodeId()
                                            && command.getCommandClassCommand() == transaction
                                                    .getExpectedCommandClassCommand()) {
                                        logger.debug("NODE {}: Command verified {}.", nodeId, command);

                                        transaction.transactionAdvance(incomingMessage);

                                        // Notify the sender
                                        notifyTransactionComplete(transaction);

                                        // Remove the transaction from the outstanding transaction list
                                        if (transaction.equals(lastTransaction)) {
                                            lastTransaction = null;
                                        }
                                        completed.add(transaction);

                                        // Handle secure transactions - these are ones where we have
                                        // requested a NONCE which we've just received, and we now need
                                        // to encrypt and send the original message
                                        if (transaction instanceof ZWaveSecureTransaction) {
                                            secureQueue
                                                    .add(((ZWaveSecureTransaction) transaction).getLinkedTransaction());
                                        }
                                    } else {
                                        logger.debug("NODE {}: Command NOT verified {}.", nodeId, command);
                                    }
                                }

                                logger.debug("Transaction completed - outstandingTransactions {}",
                                        outstandingTransactions.size());
                                outstandingTransactions.removeAll(completed);
                                logger.debug("Transaction completed - outstandingTransactions {}",
                                        outstandingTransactions.size());
                            }
                        }
                    }
                }

            }
            logger.debug("**************************** Exiting Receive Thread");
        }
    }

    private Date getNextTimer(ZWaveTransaction transaction) {
        long nextTimer = 0;
        switch (transaction.getTransactionState()) {
            case CANCELLED:
            case DONE:
            case UNINTIALIZED:
                break;
            case WAIT_RESPONSE:
                nextTimer = System.currentTimeMillis() + timer1;
                break;
            case WAIT_REQUEST:
                nextTimer = System.currentTimeMillis() + timer2;
                break;
            case WAIT_DATA:
                nextTimer = System.currentTimeMillis() + transaction.getDataTimeout();
                break;
            case ABORTED:
                nextTimer = System.currentTimeMillis() + timerAbort;
                break;
            default:
                break;
        }

        if (nextTimer == 0) {
            return null;
        }

        return new Date(nextTimer);
    }

    private ZWaveTransaction getMessageFromQueue(PriorityBlockingQueue<ZWaveTransaction> queue) {
        for (ZWaveTransaction tmp : queue) {
            ZWaveNode node = controller.getNode(tmp.getNodeId());
            if (node == null) {
                logger.debug("NODE {}: Node not found - has this node been removed?!?", tmp.getNodeId());
                continue;
            }

            // Check if the node is awake
            if (!node.isAwake()) {
                logger.debug("NODE {}: Node not awake!", node.getNodeId());
                continue;
            }

            // Node is awake - remove this transaction and return it
            queue.remove(tmp);
            return tmp;
        }

        return null;
    }

    private void sendNextMessage() {
        logger.debug("Transaction SendNextMessage {} out at start", outstandingTransactions.size());

        synchronized (sendQueue) {
            // If we're currently processing the core of a transaction, or there are too many
            // outstanding transactions, then don't start another right now.
            if (lastTransaction != null) {
                logger.debug("Transaction lastTransaction outstanding...", outstandingTransactions.size());
                return;
            }

            // If we're sending a NONCE then we want to ignore the sleeping state of the device.
            // We assume that if the device just sent us a NONCE_REQUEST then it must be awake
            ZWaveTransaction transaction = secureQueue.poll();

            if (outstandingTransactions.size() == 0) {
                // logger.debug("Transaction lastTransaction outstanding {}, {}", outstandingTransactions.size(),
                // lastTransaction);

                // } else {
                // Get a message from the different queues
                // Security first, then standard messages, then controller messages
                if (transaction == null) {
                    transaction = getMessageFromQueue(sendQueue);
                    if (transaction != null) {
                        logger.debug("Transaction from sendQueue");
                    }
                } else {
                    logger.debug("Transaction from secureQueue");
                }
                if (transaction == null) {
                    transaction = controllerQueue.poll();
                    logger.debug("Transaction from controllerQueue");
                }
            }

            if (transaction == null) {
                // Nothing to send
                logger.debug("Transaction SendNextMessage nothing");
                return;
            }

            ZWaveCommandClassPayload serialMessage;
            // If this requires security, then check if we have a NONCE
            if (transaction.getRequiresSecurity()) {
                logger.debug("NODE {}: Transaction requires security", transaction.getNodeId());
                ZWaveNode node = controller.getNode(transaction.getNodeId());
                ZWaveSecurityCommandClass securityCommandClass = (ZWaveSecurityCommandClass) node.getEndpoint(0)
                        .getCommandClass(ZWaveCommandClassEnum.COMMAND_CLASS_SECURITY);
                if (securityCommandClass == null) {
                    logger.debug("NODE {}: COMMAND_CLASS_SECURITY not found.", transaction.getNodeId());
                    return;
                }

                if (securityCommandClass.isNonceAvailable()) {
                    // We have a NONCE, so encapsulate and send
                    logger.debug("NODE {}: NONCE available so encap and send.", transaction.getNodeId());

                    ZWaveCommandClassTransactionPayload securePayload = new ZWaveCommandClassTransactionPayload(
                            transaction.getNodeId(),
                            securityCommandClass.getSecurityMessageEncapsulation(transaction.getPayloadBuffer()),
                            TransactionPriority.RealTime, transaction.getExpectedCommandClass(),
                            transaction.getExpectedCommandClassCommand());

                    // Get the serial message for the secure message and add it to our transaction so it correlates
                    // properly
                    // serialMessage = securePayload.getSerialMessage();
                    // transaction.setSerialMessage(serialMessage);
                } else {
                    // Request a nonce - create a temporary transaction
                    // We keep a reference to the original transaction so that if the nonce transaction fails, then we
                    // fail the real transaction and let the application deal with retries.
                    // transaction = new ZWaveSecureTransaction(transaction,
                    // securityCommandClass.getSecurityNonceGet());
                    // serialMessage = transaction.getSerialMessage();
                }
            } else {
                logger.debug("getTransactionToSend 6");
                // serialMessage = transaction.getSerialMessage();
            }

            // Add this message to the outstandingTransactions list
            // SerialMessage serialMessage = transaction.getSerialMessage();
            // serialMessage
            // .setTransmitOptions(TRANSMIT_OPTION_ACK | TRANSMIT_OPTION_AUTO_ROUTE | TRANSMIT_OPTION_EXPLORE);
            // controller.sendPacket(serialMessage);

            transaction.transactionStart();
            logger.debug("Transaction SendNextMessage started: {}", transaction);

            logger.debug("Transaction SendNextMessage started: expected cmd class: {}",
                    transaction.getExpectedCommandClass());
            logger.debug("Transaction SendNextMessage started: expected cmd: {}",
                    transaction.getExpectedCommandClassCommand());

            outstandingTransactions.add(transaction);
            logger.debug("Transaction SendNextMessage Transactions outstanding: {}", outstandingTransactions.size());
            transaction.setTimeout(getNextTimer(transaction));
            startTransactionTimer();
            lastTransaction = transaction;
            logger.debug("Transaction SendNextMessage lastTransaction: {}", lastTransaction);
        }
    }

    private synchronized void startTransactionTimer() {
        // Stop any existing timer
        resetTransactionTimer();

        // Find the time till the next timer
        Date nextTimer = null;
        for (ZWaveTransaction transaction : outstandingTransactions) {
            if (nextTimer == null) {
                nextTimer = transaction.getTimeout();
                continue;
            }

            Date time = transaction.getTimeout();
            if (time != null && time.before(nextTimer)) {
                nextTimer = time;
            }
        }

        // Start the timer if required
        if (nextTimer != null) {
            // Create the timer task
            timerTask = new ZWaveTransactionTimer();

            logger.debug("Start transaction timer to {} - {}ms", nextTimer,
                    (nextTimer.getTime() - System.currentTimeMillis()));
            timer.schedule(timerTask, nextTimer);
        }
    }

    private synchronized void resetTransactionTimer() {
        // Stop any existing timer
        if (timerTask != null) {
            logger.debug("STOP transaction timer");

            timerTask.cancel();
            timerTask = null;
        }
    }

    private class ZWaveTransactionTimer extends TimerTask {
        private final Logger logger = LoggerFactory.getLogger(ZWaveTransactionTimer.class);

        @Override
        public void run() {
            // if (true) {
            // return;
            // }

            synchronized (sendQueue) {
                logger.debug("XXXXXXXXX Timeout.......... {} outstanding transactions", outstandingTransactions.size());
                Date now = new Date();
                // List<ZWaveTransaction> retries = new ArrayList<ZWaveTransaction>();

                // Loop through all outstanding transactions to see if any have timed out
                Iterator<ZWaveTransaction> iterator = outstandingTransactions.iterator();
                while (iterator.hasNext()) {
                    ZWaveTransaction transaction = iterator.next();
                    Date timer = transaction.getTimeout();
                    if (timer != null && !timer.after(now)) {
                        // Timeout
                        logger.debug("NODE {}: XXXXXXX Timeout at state {}. {} retries remaining.",
                                transaction.getNodeId(), transaction.getTransactionState(),
                                transaction.getAttemptsRemaining());
                        /*
                         * // If this is a SendData message, and we're not waiting for DATA
                         * // Then we need to cancel this request.
                         * // TODO: Maybe this should be generalised to allow for other commands?
                         * if (transaction.getSerialMessageClass() == SerialMessageClass.SendData
                         * && (transaction.getTransactionState() == TransactionState.WAIT_REQUEST
                         * || transaction.getTransactionState() == TransactionState.WAIT_RESPONSE)) {
                         * // SendData requests need to be aborted - so we don't cancel the transaction.
                         * // Once aborted we will get the completion of the transaction which will cancel the
                         * // transaction.
                         * logger.debug("Aborting Transaction!");
                         * transaction.setTransactionAborted();
                         * transaction.setTimeout(getNextTimer(transaction));
                         * 
                         * controller.sendPacket(new ZWaveTransactionMessageBuilder(SerialMessageClass.SendDataAbort)
                         * .build().getSerialMessage());
                         * } else {
                         * // Remove this transaction from the outstanding transactions list
                         * iterator.remove();
                         * 
                         * if (lastTransaction == transaction) {
                         * // If this is the current transaction, then reset it.
                         * lastTransaction = null;
                         * logger.debug("Transaction is current transaction, so clearing!!!!!");
                         * }
                         * 
                         * // Resend if there are still attempts remaining
                         * // if (transaction.decrementAttemptsRemaining() <= 0) {
                         * transaction.setTransactionCanceled();
                         * controller.handleTransactionComplete(transaction, null);
                         * notifyTransactionComplete(transaction);
                         * // } else {
                         * // Resend - add to a separate list so as not to impact iterator
                         * // transaction.resetTransaction();
                         * // retries.add(transaction);
                         * // }
                         * }
                         */
                    }
                }

                // Add retries to the queue
                // for (ZWaveTransaction transaction : retries) {
                // logger.debug("Resending... Transaction " + transaction.getTransactionId());
                // addTransactionToQueue(transaction);
                // }

                // If there's no outstanding transaction, try and send one
                sendNextMessage();
                startTransactionTimer();
            }
        }
    }

    public Future<ZWaveTransactionResponse> sendTransactionAsync(
            final ZWaveCommandClassTransactionPayload transaction) {
        class TransactionWaiter implements Callable<ZWaveTransactionResponse>, TransactionListener {
            private ZWaveTransactionResponse response = null;
            private long transactionId;

            @Override
            public ZWaveTransactionResponse call() throws Exception {
                // Register a listener
                addTransactionListener(this);

                // Send the transaction
                transactionId = queueTransactionForSend(transaction);
                if (transactionId == 0) {
                    logger.debug(">>>>>>>>> transaction rejected !!");

                    // The transaction failed!
                    // Remove the listener
                    removeTransactionListener(this);

                    return response;
                }

                // Wait for the transaction to complete
                synchronized (this) {
                    while (response == null) {
                        wait();
                    }
                }

                // Remove the listener
                removeTransactionListener(this);

                logger.debug("********* Transaction Response Complete " + transactionId + " -- ");

                return response;
            }

            @Override
            public void transactionEvent(ZWaveTransaction transactionEvent) {
                logger.debug("********* Transaction event listener " + transactionId + " -- "
                        + transactionEvent.getTransactionId());

                // Check if this transaction is ours
                if (transactionEvent.getTransactionId() != transactionId) {
                    return;
                }
                logger.debug("********* Transaction event listener " + transactionId + " -- DONE -- "
                        + transactionEvent.getTransactionState() + " "
                        + transactionEvent.getTransactionCancelledState());

                // Return the response
                ZWaveTransactionResponse.State state = State.COMPLETE;
                if (transactionEvent.getTransactionState() != TransactionState.DONE) {
                    switch (transactionEvent.getTransactionCancelledState()) {
                        case CANCELLED:
                            // state = State.CANCELLED;
                            break;
                        case DONE:
                            break;
                        case UNINTIALIZED:
                            logger.debug("Completing UNINTIALIZED transaction {}!!! How?!?", transactionId);
                            break;
                        case WAIT_DATA:
                            // state = State.TIMEOUT_WAITING_FOR_DATA;
                            break;
                        case WAIT_RESPONSE:
                            // state = State.TIMEOUT_WAITING_FOR_CONTROLLER;
                            break;
                        case WAIT_REQUEST:
                            // state = State.TIMEOUT_WAITING_FOR_RESPONSE;
                            break;
                        default:
                            break;
                    }
                }
                response = new ZWaveTransactionResponse(state);

                logger.debug("NODE {}: -- To notify -- {}", transaction.getDestinationNode(), state);
                synchronized (this) {
                    notify();
                }
            }
        }

        Callable<ZWaveTransactionResponse> worker = new TransactionWaiter();
        return executor.submit(worker);
    }

    public ZWaveTransactionResponse sendTransaction(ZWaveCommandClassTransactionPayload transaction) {
        logger.debug("NODE {}: sendTransaction {}", transaction.getDestinationNode(), transaction);

        Future<ZWaveTransactionResponse> futureResponse = sendTransactionAsync(transaction);
        try {
            ZWaveTransactionResponse response = futureResponse.get();
            return response;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    interface TransactionListener {
        void transactionEvent(ZWaveTransaction transaction);
    }
}
