/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zwave.commandclass.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_MAILBOX</b> version <b>1</b>.
 * <p>
 * Command Class Mailbox
 * <p>
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassMailboxV1 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassMailboxV1.class);

    /**
     * Integer command class key for COMMAND_CLASS_MAILBOX
     */
    public final static int COMMAND_CLASS_KEY = 0x69;

    /**
     * Mailbox Configuration Get Command Constant
     */
    public final static int MAILBOX_CONFIGURATION_GET = 0x01;
    /**
     * Mailbox Configuration Set Command Constant
     */
    public final static int MAILBOX_CONFIGURATION_SET = 0x02;
    /**
     * Mailbox Configuration Report Command Constant
     */
    public final static int MAILBOX_CONFIGURATION_REPORT = 0x03;
    /**
     * Mailbox Queue Command Constant
     */
    public final static int MAILBOX_QUEUE = 0x04;
    /**
     * Mailbox Wakeup Notification Command Constant
     */
    public final static int MAILBOX_WAKEUP_NOTIFICATION = 0x05;
    /**
     * Mailbox Failing Node Command Constant
     */
    public final static int MAILBOX_NODE_FAILING = 0x06;


    /**
     * Map holding constants for Properties1
     */
    private static Map<Integer, String> constantProperties1 = new HashMap<Integer, String>();
    static {

        // Constants for Properties1
        constantProperties1.put(0x04, "LAST");
    }

    /**
     * Creates a new message with the MAILBOX_CONFIGURATION_GET command.
     * <p>
     * Mailbox Configuration Get
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMailboxConfigurationGet() {
        logger.debug("Creating command message MAILBOX_CONFIGURATION_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(MAILBOX_CONFIGURATION_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the MAILBOX_CONFIGURATION_GET command.
     * <p>
     * Mailbox Configuration Get
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMailboxConfigurationGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the MAILBOX_CONFIGURATION_SET command.
     * <p>
     * Mailbox Configuration Set
     *
     * @param mode {@link String}
     * @param forwardingDestinationIpv6Address {@link byte[]}
     * @param udpPortNumber {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMailboxConfigurationSet(String mode, byte[] forwardingDestinationIpv6Address,
            Integer udpPortNumber) {
        logger.debug("Creating command message MAILBOX_CONFIGURATION_SET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(MAILBOX_CONFIGURATION_SET);

        // Process 'Properties1'
        int valmode;
        switch (mode) {
            case "DISABLE":
                valmode = 0;
                break;
            case "ENABLE_MAILBOX_SERVICE":
                valmode = 1;
                break;
            case "ENABLE_MAILBOX_PROXY":
                valmode = 2;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for mode: " + mode);
        }
        outputData.write(valmode & 0x07);

        // Process 'Forwarding Destination IPv6 Address'
        if (forwardingDestinationIpv6Address != null) {
            if (forwardingDestinationIpv6Address.length > 16) {
                throw new IllegalArgumentException("Length of array forwardingDestinationIpv6Address exceeds maximum length of 16 bytes");
            }
            try {
                outputData.write(forwardingDestinationIpv6Address);
            } catch (IOException e) {
            }
        }

        // Process 'UDP Port Number'
        outputData.write((udpPortNumber >> 8) & 0xff);
        outputData.write(udpPortNumber & 0xff);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the MAILBOX_CONFIGURATION_SET command.
     * <p>
     * Mailbox Configuration Set
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>MODE {@link String}
     * <li>FORWARDING_DESTINATION_IPV6_ADDRESS {@link byte[]}
     * <li>UDP_PORT_NUMBER {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMailboxConfigurationSet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Properties1'
        switch (payload[2] & 0x07) {
            case 0x00:
                response.put("MODE", "DISABLE");
                break;
            case 0x01:
                response.put("MODE", "ENABLE_MAILBOX_SERVICE");
                break;
            case 0x02:
                response.put("MODE", "ENABLE_MAILBOX_PROXY");
                break;
            default:
                logger.debug("Unknown enum value {} for MODE", String.format("0x%02X", 2));
        }

        // Process 'Forwarding Destination IPv6 Address'
        int lenForwardingDestinationIpv6Address = Math.min(16, payload.length - 3);
        byte[] valForwardingDestinationIpv6Address = new byte[lenForwardingDestinationIpv6Address];
        for (int cntForwardingDestinationIpv6Address = 0; cntForwardingDestinationIpv6Address < lenForwardingDestinationIpv6Address; cntForwardingDestinationIpv6Address++) {
            valForwardingDestinationIpv6Address[cntForwardingDestinationIpv6Address] = payload[3 + cntForwardingDestinationIpv6Address];
        }
        response.put("FORWARDING_DESTINATION_IPV6_ADDRESS", valForwardingDestinationIpv6Address);

        // Process 'UDP Port Number'
        response.put("UDP_PORT_NUMBER", Integer.valueOf(((payload[19] & 0xff) << 8) + (payload[20] & 0xff)));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the MAILBOX_CONFIGURATION_REPORT command.
     * <p>
     * Mailbox Configuration Report
     *
     * @param mode {@link String}
     * @param supportedModes {@link String}
     * @param mailboxCapacity {@link Integer}
     * @param forwardingDestinationIpv6Address {@link byte[]}
     * @param udpPortNumber {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMailboxConfigurationReport(String mode, String supportedModes, Integer mailboxCapacity,
            byte[] forwardingDestinationIpv6Address, Integer udpPortNumber) {
        logger.debug("Creating command message MAILBOX_CONFIGURATION_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(MAILBOX_CONFIGURATION_REPORT);

        // Process 'Properties1'
        int valProperties1 = 0;
        int valmode;
        switch (mode) {
            case "DISABLE":
                valmode = 0;
                break;
            case "ENABLE_MAILBOX_SERVICE":
                valmode = 1;
                break;
            case "ENABLE_MAILBOX_PROXY":
                valmode = 2;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for mode: " + mode);
        }
        valProperties1 |= valmode & 0x07;
        int valsupportedModes;
        switch (supportedModes) {
            case "MAILBOX_SERVICE_SUPPORTED":
                valsupportedModes = 0;
                break;
            case "MAILBOX_PROXY_SUPPORTED":
                valsupportedModes = 1;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for supportedModes: " + supportedModes);
        }
        valProperties1 |= valsupportedModes >> 3 & 0x18;
        outputData.write(valProperties1);

        // Process 'Mailbox Capacity'
        outputData.write((mailboxCapacity >> 8) & 0xff);
        outputData.write(mailboxCapacity & 0xff);

        // Process 'Forwarding Destination IPv6 Address'
        if (forwardingDestinationIpv6Address != null) {
            if (forwardingDestinationIpv6Address.length > 16) {
                throw new IllegalArgumentException("Length of array forwardingDestinationIpv6Address exceeds maximum length of 16 bytes");
            }
            try {
                outputData.write(forwardingDestinationIpv6Address);
            } catch (IOException e) {
            }
        }

        // Process 'UDP Port Number'
        outputData.write((udpPortNumber >> 8) & 0xff);
        outputData.write(udpPortNumber & 0xff);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the MAILBOX_CONFIGURATION_REPORT command.
     * <p>
     * Mailbox Configuration Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>MODE {@link String}
     * <li>SUPPORTED_MODES {@link String}
     * <li>MAILBOX_CAPACITY {@link Integer}
     * <li>FORWARDING_DESTINATION_IPV6_ADDRESS {@link byte[]}
     * <li>UDP_PORT_NUMBER {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMailboxConfigurationReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Properties1'
        switch (payload[2] & 0x07) {
            case 0x00:
                response.put("MODE", "DISABLE");
                break;
            case 0x01:
                response.put("MODE", "ENABLE_MAILBOX_SERVICE");
                break;
            case 0x02:
                response.put("MODE", "ENABLE_MAILBOX_PROXY");
                break;
            default:
                logger.debug("Unknown enum value {} for MODE", String.format("0x%02X", 2));
        }
        switch ((payload[2] & 0x18) >> 3) {
            case 0x00:
                response.put("SUPPORTED_MODES", "MAILBOX_SERVICE_SUPPORTED");
                break;
            case 0x01:
                response.put("SUPPORTED_MODES", "MAILBOX_PROXY_SUPPORTED");
                break;
            default:
                logger.debug("Unknown enum value {} for SUPPORTED_MODES", String.format("0x%02X", 2));
        }

        // Process 'Mailbox Capacity'
        response.put("MAILBOX_CAPACITY", Integer.valueOf(((payload[3] & 0xff) << 8) + (payload[4] & 0xff)));

        // Process 'Forwarding Destination IPv6 Address'
        int lenForwardingDestinationIpv6Address = Math.min(16, payload.length - 5);
        byte[] valForwardingDestinationIpv6Address = new byte[lenForwardingDestinationIpv6Address];
        for (int cntForwardingDestinationIpv6Address = 0; cntForwardingDestinationIpv6Address < lenForwardingDestinationIpv6Address; cntForwardingDestinationIpv6Address++) {
            valForwardingDestinationIpv6Address[cntForwardingDestinationIpv6Address] = payload[5 + cntForwardingDestinationIpv6Address];
        }
        response.put("FORWARDING_DESTINATION_IPV6_ADDRESS", valForwardingDestinationIpv6Address);

        // Process 'UDP Port Number'
        response.put("UDP_PORT_NUMBER", Integer.valueOf(((payload[21] & 0xff) << 8) + (payload[22] & 0xff)));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the MAILBOX_QUEUE command.
     * <p>
     * Mailbox Queue
     *
     * @param sequenceNumber {@link Integer}
     * @param mode {@link String}
     * @param last {@link Boolean}
     * @param queueHandle {@link Integer}
     * @param mailboxEntry {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMailboxQueue(Integer sequenceNumber, String mode, Boolean last, Integer queueHandle,
            byte[] mailboxEntry) {
        logger.debug("Creating command message MAILBOX_QUEUE version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(MAILBOX_QUEUE);

        // Process 'Sequence Number'
        outputData.write(sequenceNumber);

        // Process 'Properties1'
        int valProperties1 = 0;
        int valmode;
        switch (mode) {
            case "PUSH":
                valmode = 0;
                break;
            case "POP":
                valmode = 1;
                break;
            case "WAITING":
                valmode = 2;
                break;
            case "PING":
                valmode = 3;
                break;
            case "ACK":
                valmode = 4;
                break;
            case "NACK":
                valmode = 5;
                break;
            case "QUEUE_FULL":
                valmode = 6;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for mode: " + mode);
        }
        valProperties1 |= valmode & 0x03;
        valProperties1 |= last ? 0x04 : 0;
        outputData.write(valProperties1);

        // Process 'Queue Handle'
        outputData.write(queueHandle);

        // Process 'Mailbox Entry'
        if (mailboxEntry != null) {
            try {
                outputData.write(mailboxEntry);
            } catch (IOException e) {
            }
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the MAILBOX_QUEUE command.
     * <p>
     * Mailbox Queue
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>SEQUENCE_NUMBER {@link Integer}
     * <li>MODE {@link String}
     * <li>LAST {@link Boolean}
     * <li>QUEUE_HANDLE {@link Integer}
     * <li>MAILBOX_ENTRY {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMailboxQueue(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Sequence Number'
        response.put("SEQUENCE_NUMBER", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Properties1'
        switch (payload[msgOffset] & 0x03) {
            case 0x00:
                response.put("MODE", "PUSH");
                break;
            case 0x01:
                response.put("MODE", "POP");
                break;
            case 0x02:
                response.put("MODE", "WAITING");
                break;
            case 0x03:
                response.put("MODE", "PING");
                break;
            case 0x04:
                response.put("MODE", "ACK");
                break;
            case 0x05:
                response.put("MODE", "NACK");
                break;
            case 0x06:
                response.put("MODE", "QUEUE_FULL");
                break;
            default:
                logger.debug("Unknown enum value {} for MODE", String.format("0x%02X", msgOffset));
        }
        response.put("LAST", Boolean.valueOf((payload[msgOffset] & 0x04) != 0));
        msgOffset += 1;

        // Process 'Queue Handle'
        response.put("QUEUE_HANDLE", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Mailbox Entry'
        ByteArrayOutputStream valMailboxEntry = new ByteArrayOutputStream();
        while (msgOffset < payload.length) {
            valMailboxEntry.write(payload[msgOffset]);
            msgOffset++;
        }
        response.put("MAILBOX_ENTRY", valMailboxEntry);

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the MAILBOX_WAKEUP_NOTIFICATION command.
     * <p>
     * Mailbox Wakeup Notification
     *
     * @param queueHandle {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMailboxWakeupNotification(Integer queueHandle) {
        logger.debug("Creating command message MAILBOX_WAKEUP_NOTIFICATION version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(MAILBOX_WAKEUP_NOTIFICATION);

        // Process 'Queue Handle'
        outputData.write(queueHandle);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the MAILBOX_WAKEUP_NOTIFICATION command.
     * <p>
     * Mailbox Wakeup Notification
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>QUEUE_HANDLE {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMailboxWakeupNotification(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Queue Handle'
        response.put("QUEUE_HANDLE", Integer.valueOf(payload[2]));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the MAILBOX_NODE_FAILING command.
     * <p>
     * Mailbox Failing Node
     *
     * @param queueHandle {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMailboxNodeFailing(Integer queueHandle) {
        logger.debug("Creating command message MAILBOX_NODE_FAILING version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(MAILBOX_NODE_FAILING);

        // Process 'Queue Handle'
        outputData.write(queueHandle);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the MAILBOX_NODE_FAILING command.
     * <p>
     * Mailbox Failing Node
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>QUEUE_HANDLE {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMailboxNodeFailing(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Queue Handle'
        response.put("QUEUE_HANDLE", Integer.valueOf(payload[2]));

        // Return the map of processed response data;
        return response;
    }

}
