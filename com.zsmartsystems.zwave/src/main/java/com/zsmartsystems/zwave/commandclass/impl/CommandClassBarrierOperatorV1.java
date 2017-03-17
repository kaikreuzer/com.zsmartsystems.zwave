/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zwave.commandclass.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_BARRIER_OPERATOR</b> version <b>1</b>.<br>
 *
 * Command Class Barrier Operator<br>
 *
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.<br>
 *
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassBarrierOperatorV1 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassBarrierOperatorV1.class);

    /**
     * Integer command class key for COMMAND_CLASS_BARRIER_OPERATOR
     */
    public final static int COMMAND_CLASS_KEY = 0x66;

    /**
     * Barrier Operator Set Command Constant
     */
    public final static int BARRIER_OPERATOR_SET = 0x01;
    /**
     * Barrier Operator Get Command Constant
     */
    public final static int BARRIER_OPERATOR_GET = 0x02;
    /**
     * Barrier Operator Report Command Constant
     */
    public final static int BARRIER_OPERATOR_REPORT = 0x03;
    /**
     * Barrier Operator Signal Supported Get Command Constant
     */
    public final static int BARRIER_OPERATOR_SIGNAL_SUPPORTED_GET = 0x04;
    /**
     * Barrier Operator Signal Supported Report Command Constant
     */
    public final static int BARRIER_OPERATOR_SIGNAL_SUPPORTED_REPORT = 0x05;
    /**
     * Barrier Operator Signal Set Command Constant
     */
    public final static int BARRIER_OPERATOR_SIGNAL_SET = 0x06;
    /**
     * Barrier Operator Signal Get Command Constant
     */
    public final static int BARRIER_OPERATOR_SIGNAL_GET = 0x07;
    /**
     * Barrier Operator Signal Report Command Constant
     */
    public final static int BARRIER_OPERATOR_SIGNAL_REPORT = 0x08;

    /**
     * Creates a new message with the BARRIER_OPERATOR_SET command.<br>
     *
     * Barrier Operator Set<br>
     *
     *
     * @param targetValue {@link String}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getBarrierOperatorSet(String targetValue) {
        logger.debug("Creating command message BARRIER_OPERATOR_SET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(BARRIER_OPERATOR_SET);

        // Process 'Target Value'
        switch (targetValue) {
            case "CLOSE":
                outputData.write(0x00);
                break;
            case "OPEN":
                outputData.write(0xFF);
                break;
            default:
                throw new IllegalArgumentException("Unknown constant value for targetValue: " + targetValue);
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the BARRIER_OPERATOR_SET command<br>
     *
     * Barrier Operator Set<br>
     *
     *
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>TARGET_VALUE {@link String}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleBarrierOperatorSet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Target Value'
        switch ((int) payload[2]) {
            case (byte) 0x00:
                response.put("TARGET_VALUE", "CLOSE");
                break;
            case (byte) 0xFF:
                response.put("TARGET_VALUE", "OPEN");
                break;
            default:
                logger.debug("Unknown value {} for constant 'TARGET_VALUE'", String.format("%02X", payload[2]));
                response.put("TARGET_VALUE", "UNKNOWN_" + String.format("%02X", payload[2]));
                break;
        }

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the BARRIER_OPERATOR_GET command.<br>
     *
     * Barrier Operator Get<br>
     *
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getBarrierOperatorGet() {
        logger.debug("Creating command message BARRIER_OPERATOR_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(BARRIER_OPERATOR_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the BARRIER_OPERATOR_GET command<br>
     *
     * Barrier Operator Get<br>
     *
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleBarrierOperatorGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the BARRIER_OPERATOR_REPORT command.<br>
     *
     * Barrier Operator Report<br>
     *
     *
     * @param state {@link String}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getBarrierOperatorReport(String state) {
        logger.debug("Creating command message BARRIER_OPERATOR_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(BARRIER_OPERATOR_REPORT);

        // Process 'State'
        switch (state) {
            case "CLOSED":
                outputData.write(0x00);
                break;
            case "CLOSING":
                outputData.write(0xFC);
                break;
            case "STOPPED":
                outputData.write(0xFD);
                break;
            case "OPENING":
                outputData.write(0xFE);
                break;
            case "OPEN":
                outputData.write(0xFF);
                break;
            default:
                throw new IllegalArgumentException("Unknown constant value for state: " + state);
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the BARRIER_OPERATOR_REPORT command<br>
     *
     * Barrier Operator Report<br>
     *
     *
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>STATE {@link String}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleBarrierOperatorReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'State'
        switch ((int) payload[2]) {
            case (byte) 0x00:
                response.put("STATE", "CLOSED");
                break;
            case (byte) 0xFC:
                response.put("STATE", "CLOSING");
                break;
            case (byte) 0xFD:
                response.put("STATE", "STOPPED");
                break;
            case (byte) 0xFE:
                response.put("STATE", "OPENING");
                break;
            case (byte) 0xFF:
                response.put("STATE", "OPEN");
                break;
            default:
                logger.debug("Unknown value {} for constant 'STATE'", String.format("%02X", payload[2]));
                response.put("STATE", "UNKNOWN_" + String.format("%02X", payload[2]));
                break;
        }

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the BARRIER_OPERATOR_SIGNAL_SUPPORTED_GET command.<br>
     *
     * Barrier Operator Signal Supported Get<br>
     *
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getBarrierOperatorSignalSupportedGet() {
        logger.debug("Creating command message BARRIER_OPERATOR_SIGNAL_SUPPORTED_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(BARRIER_OPERATOR_SIGNAL_SUPPORTED_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the BARRIER_OPERATOR_SIGNAL_SUPPORTED_GET command<br>
     *
     * Barrier Operator Signal Supported Get<br>
     *
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleBarrierOperatorSignalSupportedGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the BARRIER_OPERATOR_SIGNAL_SUPPORTED_REPORT command.<br>
     *
     * Barrier Operator Signal Supported Report<br>
     *
     *
     * @param bitMask {@link List<String>}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getBarrierOperatorSignalSupportedReport(List<String> bitMask) {
        logger.debug("Creating command message BARRIER_OPERATOR_SIGNAL_SUPPORTED_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(BARRIER_OPERATOR_SIGNAL_SUPPORTED_REPORT);

        // Process 'Bit Mask'
        int valBitMask = 0;
        valBitMask |= (bitMask.contains("NOT_SUPPORTED") ? 0x01 : 0);
        valBitMask |= (bitMask.contains("AUDIBLE_NOTIFICATION") ? 0x02 : 0);
        valBitMask |= (bitMask.contains("VISUAL_NOTIFICATION") ? 0x04 : 0);
        outputData.write(valBitMask);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the BARRIER_OPERATOR_SIGNAL_SUPPORTED_REPORT command<br>
     *
     * Barrier Operator Signal Supported Report<br>
     *
     *
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>BIT_MASK {@link List}<{@link String}>
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleBarrierOperatorSignalSupportedReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Bit Mask'
        List<String> responseBitMask = new ArrayList<String>();
        int cntBitMask = 0;
        while (cntBitMask < payload.length - 2) {
            if ((payload[2 + (cntBitMask / 8)] & cntBitMask % 8) == 0) {
                continue;
            }
            switch (cntBitMask) {
                case 0x00:
                    responseBitMask.add("NOT_SUPPORTED");
                    break;
                case 0x01:
                    responseBitMask.add("AUDIBLE_NOTIFICATION");
                    break;
                case 0x02:
                    responseBitMask.add("VISUAL_NOTIFICATION");
                    break;
                default:
                    responseBitMask.add("BITMASK_" + cntBitMask);
                    break;
            }
        }
        response.put("BIT_MASK", responseBitMask);

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the BARRIER_OPERATOR_SIGNAL_SET command.<br>
     *
     * Barrier Operator Signal Set<br>
     *
     *
     * @param subsystemType {@link Integer}
     * @param subsystemState {@link String}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getBarrierOperatorSignalSet(Integer subsystemType, String subsystemState) {
        logger.debug("Creating command message BARRIER_OPERATOR_SIGNAL_SET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(BARRIER_OPERATOR_SIGNAL_SET);

        // Process 'Subsystem Type'
        outputData.write(subsystemType);

        // Process 'Subsystem State'
        switch (subsystemState) {
            case "OFF":
                outputData.write(0x00);
                break;
            case "ON":
                outputData.write(0xFF);
                break;
            default:
                throw new IllegalArgumentException("Unknown constant value for subsystemState: " + subsystemState);
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the BARRIER_OPERATOR_SIGNAL_SET command<br>
     *
     * Barrier Operator Signal Set<br>
     *
     *
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>SUBSYSTEM_TYPE {@link Integer}
     * <li>SUBSYSTEM_STATE {@link String}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleBarrierOperatorSignalSet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Subsystem Type'
        switch ((int) payload[2]) {
            case 0x00:
                response.put("SUBSYSTEM_TYPE", "NOT_SUPPORTED");
                break;
            case 0x01:
                response.put("SUBSYSTEM_TYPE", "AUDIBLE_NOTIFICATION");
                break;
            case 0x02:
                response.put("SUBSYSTEM_TYPE", "VISUAL_NOTIFICATION");
                break;
            default:
                logger.debug("");
                break;
        }

        // Process 'Subsystem State'
        switch ((int) payload[3]) {
            case (byte) 0x00:
                response.put("SUBSYSTEM_STATE", "OFF");
                break;
            case (byte) 0xFF:
                response.put("SUBSYSTEM_STATE", "ON");
                break;
            default:
                logger.debug("Unknown value {} for constant 'SUBSYSTEM_STATE'", String.format("%02X", payload[3]));
                response.put("SUBSYSTEM_STATE", "UNKNOWN_" + String.format("%02X", payload[3]));
                break;
        }

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the BARRIER_OPERATOR_SIGNAL_GET command.<br>
     *
     * Barrier Operator Signal Get<br>
     *
     *
     * @param subsystemType {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getBarrierOperatorSignalGet(Integer subsystemType) {
        logger.debug("Creating command message BARRIER_OPERATOR_SIGNAL_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(BARRIER_OPERATOR_SIGNAL_GET);

        // Process 'Subsystem Type'
        outputData.write(subsystemType);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the BARRIER_OPERATOR_SIGNAL_GET command<br>
     *
     * Barrier Operator Signal Get<br>
     *
     *
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>SUBSYSTEM_TYPE {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleBarrierOperatorSignalGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Subsystem Type'
        switch ((int) payload[2]) {
            case 0x00:
                response.put("SUBSYSTEM_TYPE", "NOT_SUPPORTED");
                break;
            case 0x01:
                response.put("SUBSYSTEM_TYPE", "AUDIBLE_NOTIFICATION");
                break;
            case 0x02:
                response.put("SUBSYSTEM_TYPE", "VISUAL_NOTIFICATION");
                break;
            default:
                logger.debug("");
                break;
        }

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the BARRIER_OPERATOR_SIGNAL_REPORT command.<br>
     *
     * Barrier Operator Signal Report<br>
     *
     *
     * @param subsystemType {@link Integer}
     * @param subsystemState {@link String}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getBarrierOperatorSignalReport(Integer subsystemType, String subsystemState) {
        logger.debug("Creating command message BARRIER_OPERATOR_SIGNAL_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(BARRIER_OPERATOR_SIGNAL_REPORT);

        // Process 'Subsystem Type'
        outputData.write(subsystemType);

        // Process 'Subsystem State'
        switch (subsystemState) {
            case "OFF":
                outputData.write(0x00);
                break;
            case "ON":
                outputData.write(0xFF);
                break;
            default:
                throw new IllegalArgumentException("Unknown constant value for subsystemState: " + subsystemState);
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the BARRIER_OPERATOR_SIGNAL_REPORT command<br>
     *
     * Barrier Operator Signal Report<br>
     *
     *
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>SUBSYSTEM_TYPE {@link Integer}
     * <li>SUBSYSTEM_STATE {@link String}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleBarrierOperatorSignalReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Subsystem Type'
        switch ((int) payload[2]) {
            case 0x00:
                response.put("SUBSYSTEM_TYPE", "NOT_SUPPORTED");
                break;
            case 0x01:
                response.put("SUBSYSTEM_TYPE", "AUDIBLE_NOTIFICATION");
                break;
            case 0x02:
                response.put("SUBSYSTEM_TYPE", "VISUAL_NOTIFICATION");
                break;
            default:
                logger.debug("");
                break;
        }

        // Process 'Subsystem State'
        switch ((int) payload[3]) {
            case (byte) 0x00:
                response.put("SUBSYSTEM_STATE", "OFF");
                break;
            case (byte) 0xFF:
                response.put("SUBSYSTEM_STATE", "ON");
                break;
            default:
                logger.debug("Unknown value {} for constant 'SUBSYSTEM_STATE'", String.format("%02X", payload[3]));
                response.put("SUBSYSTEM_STATE", "UNKNOWN_" + String.format("%02X", payload[3]));
                break;
        }

        // Return the map of processed response data;
        return response;
    }

}
