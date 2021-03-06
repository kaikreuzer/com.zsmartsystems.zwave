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
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_THERMOSTAT_MODE</b> version <b>3</b>.
 * <p>
 * Command Class Thermostat Mode
 * <p>
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.
 * <p>
 * Command class key is 0x40.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassThermostatModeV3 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassThermostatModeV3.class);

    /**
     * Integer command class key for COMMAND_CLASS_THERMOSTAT_MODE
     */
    public final static int COMMAND_CLASS_KEY = 0x40;

    /**
     * Thermostat Mode Set Command Constant
     */
    public final static int THERMOSTAT_MODE_SET = 0x01;
    /**
     * Thermostat Mode Get Command Constant
     */
    public final static int THERMOSTAT_MODE_GET = 0x02;
    /**
     * Thermostat Mode Report Command Constant
     */
    public final static int THERMOSTAT_MODE_REPORT = 0x03;
    /**
     * Thermostat Mode Supported Get Command Constant
     */
    public final static int THERMOSTAT_MODE_SUPPORTED_GET = 0x04;
    /**
     * Thermostat Mode Supported Report Command Constant
     */
    public final static int THERMOSTAT_MODE_SUPPORTED_REPORT = 0x05;


    /**
     * Map holding constants for ThermostatModeSupportedReportBitMask
     */
    private static Map<Integer, String> constantThermostatModeSupportedReportBitMask = new HashMap<Integer, String>();
    static {

        // Constants for ThermostatModeSupportedReportBitMask
        constantThermostatModeSupportedReportBitMask.put(0x00, "OFF");
        constantThermostatModeSupportedReportBitMask.put(0x01, "HEAT");
        constantThermostatModeSupportedReportBitMask.put(0x02, "COOL");
        constantThermostatModeSupportedReportBitMask.put(0x03, "AUTO");
        constantThermostatModeSupportedReportBitMask.put(0x04, "AUXILIARY_EMERGENCY_HEAT");
        constantThermostatModeSupportedReportBitMask.put(0x05, "RESUME");
        constantThermostatModeSupportedReportBitMask.put(0x06, "FAN_ONLY");
        constantThermostatModeSupportedReportBitMask.put(0x07, "FURNACE");
        constantThermostatModeSupportedReportBitMask.put(0x08, "DRY_AIR");
        constantThermostatModeSupportedReportBitMask.put(0x09, "MOIST_AIR");
        constantThermostatModeSupportedReportBitMask.put(0x0A, "AUTO_CHANGEOVER");
        constantThermostatModeSupportedReportBitMask.put(0x0B, "ENERGY_SAVE_HEAT");
        constantThermostatModeSupportedReportBitMask.put(0x0C, "ENERGY_SAVE_COOL");
        constantThermostatModeSupportedReportBitMask.put(0x0D, "AWAY");
        constantThermostatModeSupportedReportBitMask.put(0x1F, "MANUFACTURER_SPECIFC");
        constantThermostatModeSupportedReportBitMask.put(0x0F, "FULL_POWER");
    }

    /**
     * Creates a new message with the THERMOSTAT_MODE_SET command.
     * <p>
     * Thermostat Mode Set
     *
     * @param mode {@link String}
     * @param noOfManufacturerDataFields {@link Integer}
     * @param manufacturerData {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getThermostatModeSet(String mode, Integer noOfManufacturerDataFields, byte[] manufacturerData) {
        logger.debug("Creating command message THERMOSTAT_MODE_SET version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(THERMOSTAT_MODE_SET);

        // Process 'Level'
        int valLevel = 0;
        int valmode;
        switch (mode) {
            case "OFF":
                valmode = 0;
                break;
            case "HEAT":
                valmode = 1;
                break;
            case "COOL":
                valmode = 2;
                break;
            case "AUTO":
                valmode = 3;
                break;
            case "AUXILIARY_HEAT":
                valmode = 4;
                break;
            case "RESUME":
                valmode = 5;
                break;
            case "FAN_ONLY":
                valmode = 6;
                break;
            case "FURNACE":
                valmode = 7;
                break;
            case "DRY_AIR":
                valmode = 8;
                break;
            case "MOIST_AIR":
                valmode = 9;
                break;
            case "AUTO_CHANGEOVER":
                valmode = 10;
                break;
            case "ENERGY_SAVE_HEAT":
                valmode = 11;
                break;
            case "ENERGY_SAVE_COOL":
                valmode = 12;
                break;
            case "AWAY":
                valmode = 13;
                break;
            case "FULL_POWER":
                valmode = 15;
                break;
            case "MANUFACTURER_SPECIFC":
                valmode = 31;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for mode: " + mode);
        }
        valLevel |= valmode & 0x1F;
        valLevel |= ((noOfManufacturerDataFields << 5) & 0xE0);
        outputData.write(valLevel);

        // Process 'Manufacturer Data'
        if (manufacturerData != null) {
            try {
                outputData.write(manufacturerData);
            } catch (IOException e) {
            }
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the THERMOSTAT_MODE_SET command.
     * <p>
     * Thermostat Mode Set
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>MODE {@link String}
     * <li>NO_OF_MANUFACTURER_DATA_FIELDS {@link Integer}
     * <li>MANUFACTURER_DATA {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleThermostatModeSet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Level'
        switch (payload[msgOffset] & 0x1F) {
            case 0x00:
                response.put("MODE", "OFF");
                break;
            case 0x01:
                response.put("MODE", "HEAT");
                break;
            case 0x02:
                response.put("MODE", "COOL");
                break;
            case 0x03:
                response.put("MODE", "AUTO");
                break;
            case 0x04:
                response.put("MODE", "AUXILIARY_HEAT");
                break;
            case 0x05:
                response.put("MODE", "RESUME");
                break;
            case 0x06:
                response.put("MODE", "FAN_ONLY");
                break;
            case 0x07:
                response.put("MODE", "FURNACE");
                break;
            case 0x08:
                response.put("MODE", "DRY_AIR");
                break;
            case 0x09:
                response.put("MODE", "MOIST_AIR");
                break;
            case 0x0A:
                response.put("MODE", "AUTO_CHANGEOVER");
                break;
            case 0x0B:
                response.put("MODE", "ENERGY_SAVE_HEAT");
                break;
            case 0x0C:
                response.put("MODE", "ENERGY_SAVE_COOL");
                break;
            case 0x0D:
                response.put("MODE", "AWAY");
                break;
            case 0x0F:
                response.put("MODE", "FULL_POWER");
                break;
            case 0x1F:
                response.put("MODE", "MANUFACTURER_SPECIFC");
                break;
            default:
                logger.debug("Unknown enum value {} for MODE", String.format("0x%02X", msgOffset));
        }
        response.put("NO_OF_MANUFACTURER_DATA_FIELDS", Integer.valueOf(payload[msgOffset] & 0xE0 >> 5));
        msgOffset += 1;

        // Process 'Manufacturer Data'
        response.put("MANUFACTURER_DATA", Integer.valueOf(payload[msgOffset]));
        msgOffset += payload[msgOffset - 1];

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the THERMOSTAT_MODE_GET command.
     * <p>
     * Thermostat Mode Get
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getThermostatModeGet() {
        logger.debug("Creating command message THERMOSTAT_MODE_GET version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(THERMOSTAT_MODE_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the THERMOSTAT_MODE_GET command.
     * <p>
     * Thermostat Mode Get
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleThermostatModeGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the THERMOSTAT_MODE_REPORT command.
     * <p>
     * Thermostat Mode Report
     *
     * @param mode {@link String}
     * @param noOfManufacturerDataFields {@link Integer}
     * @param manufacturerData {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getThermostatModeReport(String mode, Integer noOfManufacturerDataFields,
            byte[] manufacturerData) {
        logger.debug("Creating command message THERMOSTAT_MODE_REPORT version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(THERMOSTAT_MODE_REPORT);

        // Process 'Level'
        int valLevel = 0;
        int valmode;
        switch (mode) {
            case "OFF":
                valmode = 0;
                break;
            case "HEAT":
                valmode = 1;
                break;
            case "COOL":
                valmode = 2;
                break;
            case "AUTO":
                valmode = 3;
                break;
            case "AUXILIARY_HEAT":
                valmode = 4;
                break;
            case "RESUME":
                valmode = 5;
                break;
            case "FAN_ONLY":
                valmode = 6;
                break;
            case "FURNACE":
                valmode = 7;
                break;
            case "DRY_AIR":
                valmode = 8;
                break;
            case "MOIST_AIR":
                valmode = 9;
                break;
            case "AUTO_CHANGEOVER":
                valmode = 10;
                break;
            case "ENERGY_SAVE_HEAT":
                valmode = 11;
                break;
            case "ENERGY_SAVE_COOL":
                valmode = 12;
                break;
            case "AWAY":
                valmode = 13;
                break;
            case "FULL_POWER":
                valmode = 15;
                break;
            case "MANUFACTURER_SPECIFC":
                valmode = 31;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for mode: " + mode);
        }
        valLevel |= valmode & 0x1F;
        valLevel |= ((noOfManufacturerDataFields << 5) & 0xE0);
        outputData.write(valLevel);

        // Process 'Manufacturer Data'
        if (manufacturerData != null) {
            try {
                outputData.write(manufacturerData);
            } catch (IOException e) {
            }
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the THERMOSTAT_MODE_REPORT command.
     * <p>
     * Thermostat Mode Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>MODE {@link String}
     * <li>NO_OF_MANUFACTURER_DATA_FIELDS {@link Integer}
     * <li>MANUFACTURER_DATA {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleThermostatModeReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Level'
        switch (payload[msgOffset] & 0x1F) {
            case 0x00:
                response.put("MODE", "OFF");
                break;
            case 0x01:
                response.put("MODE", "HEAT");
                break;
            case 0x02:
                response.put("MODE", "COOL");
                break;
            case 0x03:
                response.put("MODE", "AUTO");
                break;
            case 0x04:
                response.put("MODE", "AUXILIARY_HEAT");
                break;
            case 0x05:
                response.put("MODE", "RESUME");
                break;
            case 0x06:
                response.put("MODE", "FAN_ONLY");
                break;
            case 0x07:
                response.put("MODE", "FURNACE");
                break;
            case 0x08:
                response.put("MODE", "DRY_AIR");
                break;
            case 0x09:
                response.put("MODE", "MOIST_AIR");
                break;
            case 0x0A:
                response.put("MODE", "AUTO_CHANGEOVER");
                break;
            case 0x0B:
                response.put("MODE", "ENERGY_SAVE_HEAT");
                break;
            case 0x0C:
                response.put("MODE", "ENERGY_SAVE_COOL");
                break;
            case 0x0D:
                response.put("MODE", "AWAY");
                break;
            case 0x0F:
                response.put("MODE", "FULL_POWER");
                break;
            case 0x1F:
                response.put("MODE", "MANUFACTURER_SPECIFC");
                break;
            default:
                logger.debug("Unknown enum value {} for MODE", String.format("0x%02X", msgOffset));
        }
        response.put("NO_OF_MANUFACTURER_DATA_FIELDS", Integer.valueOf(payload[msgOffset] & 0xE0 >> 5));
        msgOffset += 1;

        // Process 'Manufacturer Data'
        response.put("MANUFACTURER_DATA", Integer.valueOf(payload[msgOffset]));
        msgOffset += payload[msgOffset - 1];

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the THERMOSTAT_MODE_SUPPORTED_GET command.
     * <p>
     * Thermostat Mode Supported Get
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getThermostatModeSupportedGet() {
        logger.debug("Creating command message THERMOSTAT_MODE_SUPPORTED_GET version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(THERMOSTAT_MODE_SUPPORTED_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the THERMOSTAT_MODE_SUPPORTED_GET command.
     * <p>
     * Thermostat Mode Supported Get
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleThermostatModeSupportedGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the THERMOSTAT_MODE_SUPPORTED_REPORT command.
     * <p>
     * Thermostat Mode Supported Report
     *
     * @param bitMask {@link List<String>}
     *            Can be one of the following -:
     *            <ul>
     *            <li>OFF
     *            <li>HEAT
     *            <li>COOL
     *            <li>AUTO
     *            <li>AUXILIARY_EMERGENCY_HEAT
     *            <li>RESUME
     *            <li>FAN_ONLY
     *            <li>FURNACE
     *            <li>DRY_AIR
     *            <li>MOIST_AIR
     *            <li>AUTO_CHANGEOVER
     *            <li>ENERGY_SAVE_HEAT
     *            <li>ENERGY_SAVE_COOL
     *            <li>AWAY
     *            <li>MANUFACTURER_SPECIFC
     *            <li>FULL_POWER
     *            </ul>
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getThermostatModeSupportedReport(List<String> bitMask) {
        logger.debug("Creating command message THERMOSTAT_MODE_SUPPORTED_REPORT version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(THERMOSTAT_MODE_SUPPORTED_REPORT);

        // Process 'Bit Mask'
        int valBitMask = 0;
        for (String value : bitMask) {
            boolean foundBitMask = false;
            for (Integer entry : constantThermostatModeSupportedReportBitMask.keySet()) {
                if (constantThermostatModeSupportedReportBitMask.get(entry).equals(value)) {
                    foundBitMask = true;
                    valBitMask += entry;
                    break;
                }
            }
            if (!foundBitMask) {
                throw new IllegalArgumentException("Unknown constant value '" + bitMask + "' for bitMask");
            }
        }
        outputData.write(valBitMask);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the THERMOSTAT_MODE_SUPPORTED_REPORT command.
     * <p>
     * Thermostat Mode Supported Report
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
    public static Map<String, Object> handleThermostatModeSupportedReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Bit Mask'
        List<String> responseBitMask = new ArrayList<String>();
        for (int cntBitMask = 0; cntBitMask < (payload.length - 2) * 8; cntBitMask++) {
            if ((payload[2 + (cntBitMask / 8)] & (1 << cntBitMask % 8)) == 0) {
                continue;
            }
            responseBitMask.add(constantThermostatModeSupportedReportBitMask.get(cntBitMask));
        }
        response.put("BIT_MASK", responseBitMask);

        // Return the map of processed response data;
        return response;
    }

}
