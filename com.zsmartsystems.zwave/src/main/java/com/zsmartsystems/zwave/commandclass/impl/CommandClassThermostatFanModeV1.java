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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_THERMOSTAT_FAN_MODE</b> version <b>1</b>.<br>
 * <p>
 * Command Class Thermostat Fan Mode<br>
 *
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.<br>
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassThermostatFanModeV1 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassThermostatFanModeV1.class);

    /**
     * Integer command class key for COMMAND_CLASS_THERMOSTAT_FAN_MODE
     */
    public final static int COMMAND_CLASS_KEY = 0x44;

    /**
     * Thermostat Fan Mode Set Command Constant
     */
    public final static int THERMOSTAT_FAN_MODE_SET = 0x01;
    /**
     * Thermostat Fan Mode Get Command Constant
     */
    public final static int THERMOSTAT_FAN_MODE_GET = 0x02;
    /**
     * Thermostat Fan Mode Report Command Constant
     */
    public final static int THERMOSTAT_FAN_MODE_REPORT = 0x03;
    /**
     * Thermostat Fan Mode Supported Get Command Constant
     */
    public final static int THERMOSTAT_FAN_MODE_SUPPORTED_GET = 0x04;
    /**
     * Thermostat Fan Mode Supported Report Command Constant
     */
    public final static int THERMOSTAT_FAN_MODE_SUPPORTED_REPORT = 0x05;

    /**
     * Creates a new message with the THERMOSTAT_FAN_MODE_SET command.<br>
     * <p>
     * Thermostat Fan Mode Set<br>
     *
     * @param fanMode {@link String}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getThermostatFanModeSet(String fanMode) {
        logger.debug("Creating command message THERMOSTAT_FAN_MODE_SET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(THERMOSTAT_FAN_MODE_SET);

        // Process 'Level'
        int valfanMode;
        switch (fanMode) {
            case "AUTO_LOW":
                valfanMode = 0;
                break;
            case "LOW":
                valfanMode = 1;
                break;
            case "AUTO_HIGH":
                valfanMode = 2;
                break;
            case "HIGH":
                valfanMode = 3;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for fanMode: " + fanMode);
        }
        outputData.write(valfanMode & 0x0F);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the THERMOSTAT_FAN_MODE_SET command<br>
     * <p>
     * Thermostat Fan Mode Set<br>
     * <p>
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>FAN_MODE {@link String}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleThermostatFanModeSet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Level'
        switch ((int) payload[2] & 0x0F) {
            case 0x00:
                response.put("FAN_MODE", "AUTO_LOW");
                break;
            case 0x01:
                response.put("FAN_MODE", "LOW");
                break;
            case 0x02:
                response.put("FAN_MODE", "AUTO_HIGH");
                break;
            case 0x03:
                response.put("FAN_MODE", "HIGH");
                break;
            default:
                logger.debug("Unknown enum value {} for FAN_MODE", String.format("0x%02X", 2));
        }

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the THERMOSTAT_FAN_MODE_GET command.<br>
     * <p>
     * Thermostat Fan Mode Get<br>
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getThermostatFanModeGet() {
        logger.debug("Creating command message THERMOSTAT_FAN_MODE_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(THERMOSTAT_FAN_MODE_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the THERMOSTAT_FAN_MODE_GET command<br>
     * <p>
     * Thermostat Fan Mode Get<br>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleThermostatFanModeGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the THERMOSTAT_FAN_MODE_REPORT command.<br>
     * <p>
     * Thermostat Fan Mode Report<br>
     *
     * @param fanMode {@link String}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getThermostatFanModeReport(String fanMode) {
        logger.debug("Creating command message THERMOSTAT_FAN_MODE_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(THERMOSTAT_FAN_MODE_REPORT);

        // Process 'Level'
        int valfanMode;
        switch (fanMode) {
            case "AUTO_LOW":
                valfanMode = 0;
                break;
            case "LOW":
                valfanMode = 1;
                break;
            case "AUTO_HIGH":
                valfanMode = 2;
                break;
            case "HIGH":
                valfanMode = 3;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for fanMode: " + fanMode);
        }
        outputData.write(valfanMode & 0x0F);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the THERMOSTAT_FAN_MODE_REPORT command<br>
     * <p>
     * Thermostat Fan Mode Report<br>
     * <p>
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>FAN_MODE {@link String}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleThermostatFanModeReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Level'
        switch ((int) payload[2] & 0x0F) {
            case 0x00:
                response.put("FAN_MODE", "AUTO_LOW");
                break;
            case 0x01:
                response.put("FAN_MODE", "LOW");
                break;
            case 0x02:
                response.put("FAN_MODE", "AUTO_HIGH");
                break;
            case 0x03:
                response.put("FAN_MODE", "HIGH");
                break;
            default:
                logger.debug("Unknown enum value {} for FAN_MODE", String.format("0x%02X", 2));
        }

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the THERMOSTAT_FAN_MODE_SUPPORTED_GET command.<br>
     * <p>
     * Thermostat Fan Mode Supported Get<br>
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getThermostatFanModeSupportedGet() {
        logger.debug("Creating command message THERMOSTAT_FAN_MODE_SUPPORTED_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(THERMOSTAT_FAN_MODE_SUPPORTED_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the THERMOSTAT_FAN_MODE_SUPPORTED_GET command<br>
     * <p>
     * Thermostat Fan Mode Supported Get<br>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleThermostatFanModeSupportedGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the THERMOSTAT_FAN_MODE_SUPPORTED_REPORT command.<br>
     * <p>
     * Thermostat Fan Mode Supported Report<br>
     *
     * @param bitMask {@link List<String>}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getThermostatFanModeSupportedReport(List<String> bitMask) {
        logger.debug("Creating command message THERMOSTAT_FAN_MODE_SUPPORTED_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(THERMOSTAT_FAN_MODE_SUPPORTED_REPORT);

        // Process 'Bit Mask'
        int valBitMask = 0;
        valBitMask |= (bitMask.contains("AUTO") ? 0x01 : 0);
        valBitMask |= (bitMask.contains("ON") ? 0x02 : 0);
        valBitMask |= (bitMask.contains("AUTO_HIGH") ? 0x04 : 0);
        valBitMask |= (bitMask.contains("ON_HIGH") ? 0x08 : 0);
        outputData.write(valBitMask);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the THERMOSTAT_FAN_MODE_SUPPORTED_REPORT command<br>
     * <p>
     * Thermostat Fan Mode Supported Report<br>
     * <p>
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>BIT_MASK {@link List}<{@link String}>
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleThermostatFanModeSupportedReport(byte[] payload) {
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
                    responseBitMask.add("AUTO");
                    break;
                case 0x01:
                    responseBitMask.add("ON");
                    break;
                case 0x02:
                    responseBitMask.add("AUTO_HIGH");
                    break;
                case 0x03:
                    responseBitMask.add("ON_HIGH");
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

}
