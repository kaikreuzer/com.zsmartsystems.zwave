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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_THERMOSTAT_SETPOINT</b> version <b>1</b>.
 * <p>
 * Command Class Thermostat Setpoint
 * <p>
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassThermostatSetpointV1 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassThermostatSetpointV1.class);

    /**
     * Integer command class key for COMMAND_CLASS_THERMOSTAT_SETPOINT
     */
    public final static int COMMAND_CLASS_KEY = 0x43;

    /**
     * Thermostat Setpoint Set Command Constant
     */
    public final static int THERMOSTAT_SETPOINT_SET = 0x01;
    /**
     * Thermostat Setpoint Get Command Constant
     */
    public final static int THERMOSTAT_SETPOINT_GET = 0x02;
    /**
     * Thermostat Setpoint Report Command Constant
     */
    public final static int THERMOSTAT_SETPOINT_REPORT = 0x03;
    /**
     * Thermostat Setpoint Supported Get Command Constant
     */
    public final static int THERMOSTAT_SETPOINT_SUPPORTED_GET = 0x04;
    /**
     * Thermostat Setpoint Supported Report Command Constant
     */
    public final static int THERMOSTAT_SETPOINT_SUPPORTED_REPORT = 0x05;


    /**
     * Map holding constants for Bit Mask
     */
    private static Map<Integer, String> constantBitMask = new HashMap<Integer, String>();
    static {

        // Constants for Bit Mask
        constantBitMask.put(0x00, "NONE");
        constantBitMask.put(0x01, "HEATING");
        constantBitMask.put(0x02, "COOLING");
        constantBitMask.put(0x07, "FURNACE");
        constantBitMask.put(0x08, "DRY_AIR");
        constantBitMask.put(0x09, "MOIST_AIR");
        constantBitMask.put(0x0A, "AUTO_CHANGEOVER");
    }

    /**
     * Creates a new message with the THERMOSTAT_SETPOINT_SET command.
     * <p>
     * Thermostat Setpoint Set
     *
     * @param setpointType {@link String}
     * @param scale {@link Integer}
     * @param precision {@link Integer}
     * @param value {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getThermostatSetpointSet(String setpointType, Integer scale, Integer precision, byte[] value) {
        logger.debug("Creating command message THERMOSTAT_SETPOINT_SET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(THERMOSTAT_SETPOINT_SET);

        // Process 'Level'
        int valsetpointType;
        switch (setpointType) {
            case "NOT_SUPPORTED":
                valsetpointType = 0;
                break;
            case "HEATING_1":
                valsetpointType = 1;
                break;
            case "COOLING_1":
                valsetpointType = 2;
                break;
            case "NOT_SUPPORTED1":
                valsetpointType = 3;
                break;
            case "NOT_SUPPORTED2":
                valsetpointType = 4;
                break;
            case "NOT_SUPPORTED3":
                valsetpointType = 5;
                break;
            case "NOT_SUPPORTED4":
                valsetpointType = 6;
                break;
            case "FURNACE":
                valsetpointType = 7;
                break;
            case "DRY_AIR":
                valsetpointType = 8;
                break;
            case "MOIST_AIR":
                valsetpointType = 9;
                break;
            case "AUTO_CHANGEOVER":
                valsetpointType = 10;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for setpointType: " + setpointType);
        }
        outputData.write(valsetpointType & 0x0F);

        // Process 'Level2'
        // Size is used by 'Value'
        int size = value.length;
        int valLevel2 = 0;
        valLevel2 |= size & 0x07;
        valLevel2 |= ((scale << 3) & 0x18);
        valLevel2 |= ((precision << 5) & 0xE0);
        outputData.write(valLevel2);

        // Process 'Value'
        if (value != null) {
            try {
                outputData.write(value);
            } catch (IOException e) {
            }
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the THERMOSTAT_SETPOINT_SET command.
     * <p>
     * Thermostat Setpoint Set
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>SETPOINT_TYPE {@link String}
     * <li>SCALE {@link Integer}
     * <li>PRECISION {@link Integer}
     * <li>VALUE {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleThermostatSetpointSet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Level'
        switch (payload[msgOffset] & 0x0F) {
            case 0x00:
                response.put("SETPOINT_TYPE", "NOT_SUPPORTED");
                break;
            case 0x01:
                response.put("SETPOINT_TYPE", "HEATING_1");
                break;
            case 0x02:
                response.put("SETPOINT_TYPE", "COOLING_1");
                break;
            case 0x03:
                response.put("SETPOINT_TYPE", "NOT_SUPPORTED1");
                break;
            case 0x04:
                response.put("SETPOINT_TYPE", "NOT_SUPPORTED2");
                break;
            case 0x05:
                response.put("SETPOINT_TYPE", "NOT_SUPPORTED3");
                break;
            case 0x06:
                response.put("SETPOINT_TYPE", "NOT_SUPPORTED4");
                break;
            case 0x07:
                response.put("SETPOINT_TYPE", "FURNACE");
                break;
            case 0x08:
                response.put("SETPOINT_TYPE", "DRY_AIR");
                break;
            case 0x09:
                response.put("SETPOINT_TYPE", "MOIST_AIR");
                break;
            case 0x0A:
                response.put("SETPOINT_TYPE", "AUTO_CHANGEOVER");
                break;
            default:
                logger.debug("Unknown enum value {} for SETPOINT_TYPE", String.format("0x%02X", msgOffset));
        }
        msgOffset += 1;

        // Process 'Level2'
        // Size is used by 'Value'
        int varSize = payload[msgOffset] & 0x07;
        response.put("SCALE", Integer.valueOf(payload[msgOffset] & 0x18 >> 3));
        response.put("PRECISION", Integer.valueOf(payload[msgOffset] & 0xE0 >> 5));
        msgOffset += 1;

        // Process 'Value'
        ByteArrayOutputStream valValue = new ByteArrayOutputStream();
        for (int cntValue = 0; cntValue < varSize; cntValue++) {
            valValue.write(payload[msgOffset + cntValue]);
        }
        response.put("VALUE", valValue.toByteArray());
        msgOffset += varSize;

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the THERMOSTAT_SETPOINT_GET command.
     * <p>
     * Thermostat Setpoint Get
     *
     * @param setpointType {@link String}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getThermostatSetpointGet(String setpointType) {
        logger.debug("Creating command message THERMOSTAT_SETPOINT_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(THERMOSTAT_SETPOINT_GET);

        // Process 'Level'
        int valsetpointType;
        switch (setpointType) {
            case "NOT_SUPPORTED":
                valsetpointType = 0;
                break;
            case "HEATING_1":
                valsetpointType = 1;
                break;
            case "COOLING_1":
                valsetpointType = 2;
                break;
            case "NOT_SUPPORTED1":
                valsetpointType = 3;
                break;
            case "NOT_SUPPORTED2":
                valsetpointType = 4;
                break;
            case "NOT_SUPPORTED3":
                valsetpointType = 5;
                break;
            case "NOT_SUPPORTED4":
                valsetpointType = 6;
                break;
            case "FURNACE":
                valsetpointType = 7;
                break;
            case "DRY_AIR":
                valsetpointType = 8;
                break;
            case "MOIST_AIR":
                valsetpointType = 9;
                break;
            case "AUTO_CHANGEOVER":
                valsetpointType = 10;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for setpointType: " + setpointType);
        }
        outputData.write(valsetpointType & 0x0F);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the THERMOSTAT_SETPOINT_GET command.
     * <p>
     * Thermostat Setpoint Get
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>SETPOINT_TYPE {@link String}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleThermostatSetpointGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Level'
        switch (payload[2] & 0x0F) {
            case 0x00:
                response.put("SETPOINT_TYPE", "NOT_SUPPORTED");
                break;
            case 0x01:
                response.put("SETPOINT_TYPE", "HEATING_1");
                break;
            case 0x02:
                response.put("SETPOINT_TYPE", "COOLING_1");
                break;
            case 0x03:
                response.put("SETPOINT_TYPE", "NOT_SUPPORTED1");
                break;
            case 0x04:
                response.put("SETPOINT_TYPE", "NOT_SUPPORTED2");
                break;
            case 0x05:
                response.put("SETPOINT_TYPE", "NOT_SUPPORTED3");
                break;
            case 0x06:
                response.put("SETPOINT_TYPE", "NOT_SUPPORTED4");
                break;
            case 0x07:
                response.put("SETPOINT_TYPE", "FURNACE");
                break;
            case 0x08:
                response.put("SETPOINT_TYPE", "DRY_AIR");
                break;
            case 0x09:
                response.put("SETPOINT_TYPE", "MOIST_AIR");
                break;
            case 0x0A:
                response.put("SETPOINT_TYPE", "AUTO_CHANGEOVER");
                break;
            default:
                logger.debug("Unknown enum value {} for SETPOINT_TYPE", String.format("0x%02X", 2));
        }

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the THERMOSTAT_SETPOINT_REPORT command.
     * <p>
     * Thermostat Setpoint Report
     *
     * @param setpointType {@link String}
     * @param scale {@link Integer}
     * @param precision {@link Integer}
     * @param value {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getThermostatSetpointReport(String setpointType, Integer scale, Integer precision, byte[] value) {
        logger.debug("Creating command message THERMOSTAT_SETPOINT_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(THERMOSTAT_SETPOINT_REPORT);

        // Process 'Level'
        int valsetpointType;
        switch (setpointType) {
            case "NOT_SUPPORTED":
                valsetpointType = 0;
                break;
            case "HEATING_1":
                valsetpointType = 1;
                break;
            case "COOLING_1":
                valsetpointType = 2;
                break;
            case "NOT_SUPPORTED1":
                valsetpointType = 3;
                break;
            case "NOT_SUPPORTED2":
                valsetpointType = 4;
                break;
            case "NOT_SUPPORTED3":
                valsetpointType = 5;
                break;
            case "NOT_SUPPORTED4":
                valsetpointType = 6;
                break;
            case "FURNACE":
                valsetpointType = 7;
                break;
            case "DRY_AIR":
                valsetpointType = 8;
                break;
            case "MOIST_AIR":
                valsetpointType = 9;
                break;
            case "AUTO_CHANGEOVER":
                valsetpointType = 10;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for setpointType: " + setpointType);
        }
        outputData.write(valsetpointType & 0x0F);

        // Process 'Level2'
        // Size is used by 'Value'
        int size = value.length;
        int valLevel2 = 0;
        valLevel2 |= size & 0x07;
        valLevel2 |= ((scale << 3) & 0x18);
        valLevel2 |= ((precision << 5) & 0xE0);
        outputData.write(valLevel2);

        // Process 'Value'
        if (value != null) {
            try {
                outputData.write(value);
            } catch (IOException e) {
            }
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the THERMOSTAT_SETPOINT_REPORT command.
     * <p>
     * Thermostat Setpoint Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>SETPOINT_TYPE {@link String}
     * <li>SCALE {@link Integer}
     * <li>PRECISION {@link Integer}
     * <li>VALUE {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleThermostatSetpointReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Level'
        switch (payload[msgOffset] & 0x0F) {
            case 0x00:
                response.put("SETPOINT_TYPE", "NOT_SUPPORTED");
                break;
            case 0x01:
                response.put("SETPOINT_TYPE", "HEATING_1");
                break;
            case 0x02:
                response.put("SETPOINT_TYPE", "COOLING_1");
                break;
            case 0x03:
                response.put("SETPOINT_TYPE", "NOT_SUPPORTED1");
                break;
            case 0x04:
                response.put("SETPOINT_TYPE", "NOT_SUPPORTED2");
                break;
            case 0x05:
                response.put("SETPOINT_TYPE", "NOT_SUPPORTED3");
                break;
            case 0x06:
                response.put("SETPOINT_TYPE", "NOT_SUPPORTED4");
                break;
            case 0x07:
                response.put("SETPOINT_TYPE", "FURNACE");
                break;
            case 0x08:
                response.put("SETPOINT_TYPE", "DRY_AIR");
                break;
            case 0x09:
                response.put("SETPOINT_TYPE", "MOIST_AIR");
                break;
            case 0x0A:
                response.put("SETPOINT_TYPE", "AUTO_CHANGEOVER");
                break;
            default:
                logger.debug("Unknown enum value {} for SETPOINT_TYPE", String.format("0x%02X", msgOffset));
        }
        msgOffset += 1;

        // Process 'Level2'
        // Size is used by 'Value'
        int varSize = payload[msgOffset] & 0x07;
        response.put("SCALE", Integer.valueOf(payload[msgOffset] & 0x18 >> 3));
        response.put("PRECISION", Integer.valueOf(payload[msgOffset] & 0xE0 >> 5));
        msgOffset += 1;

        // Process 'Value'
        ByteArrayOutputStream valValue = new ByteArrayOutputStream();
        for (int cntValue = 0; cntValue < varSize; cntValue++) {
            valValue.write(payload[msgOffset + cntValue]);
        }
        response.put("VALUE", valValue.toByteArray());
        msgOffset += varSize;

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the THERMOSTAT_SETPOINT_SUPPORTED_GET command.
     * <p>
     * Thermostat Setpoint Supported Get
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getThermostatSetpointSupportedGet() {
        logger.debug("Creating command message THERMOSTAT_SETPOINT_SUPPORTED_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(THERMOSTAT_SETPOINT_SUPPORTED_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the THERMOSTAT_SETPOINT_SUPPORTED_GET command.
     * <p>
     * Thermostat Setpoint Supported Get
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleThermostatSetpointSupportedGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the THERMOSTAT_SETPOINT_SUPPORTED_REPORT command.
     * <p>
     * Thermostat Setpoint Supported Report
     *
     * @param bitMask {@link List<String>}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getThermostatSetpointSupportedReport(List<String> bitMask) {
        logger.debug("Creating command message THERMOSTAT_SETPOINT_SUPPORTED_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(THERMOSTAT_SETPOINT_SUPPORTED_REPORT);

        // Process 'Bit Mask'
        int valBitMask = 0;
        valBitMask |= (bitMask.contains("NONE") ? 0x01 : 0);
        valBitMask |= (bitMask.contains("HEATING") ? 0x02 : 0);
        valBitMask |= (bitMask.contains("COOLING") ? 0x04 : 0);
        valBitMask |= (bitMask.contains("FURNACE") ? 0x80 : 0);
        outputData.write(valBitMask);
        valBitMask = 0;
        valBitMask |= (bitMask.contains("DRY_AIR") ? 0x01 : 0);
        valBitMask |= (bitMask.contains("MOIST_AIR") ? 0x02 : 0);
        valBitMask |= (bitMask.contains("AUTO_CHANGEOVER") ? 0x04 : 0);
        outputData.write(valBitMask);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the THERMOSTAT_SETPOINT_SUPPORTED_REPORT command.
     * <p>
     * Thermostat Setpoint Supported Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>BIT_MASK {@link List}<{@link String}>
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleThermostatSetpointSupportedReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Bit Mask'
        List<String> responseBitMask = new ArrayList<String>();
        for (int cntBitMask = 0; cntBitMask < (payload.length - 2) * 8; cntBitMask++) {
            if ((payload[2 + (cntBitMask / 8)] & (1 << cntBitMask % 8)) == 0) {
                continue;
            }
            responseBitMask.add(constantBitMask.get(cntBitMask));
        }
        response.put("BIT_MASK", responseBitMask);

        // Return the map of processed response data;
        return response;
    }

}
