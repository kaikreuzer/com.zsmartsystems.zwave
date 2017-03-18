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
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_HRV_CONTROL</b> version <b>1</b>.<br>
 * <p>
 * Command Class Hrv Control<br>
 *
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.<br>
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassHrvControlV1 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassHrvControlV1.class);

    /**
     * Integer command class key for COMMAND_CLASS_HRV_CONTROL
     */
    public final static int COMMAND_CLASS_KEY = 0x39;

    /**
     * Hrv Control Mode Set Command Constant
     */
    public final static int HRV_CONTROL_MODE_SET = 0x01;
    /**
     * Hrv Control Mode Get Command Constant
     */
    public final static int HRV_CONTROL_MODE_GET = 0x02;
    /**
     * Hrv Control Mode Report Command Constant
     */
    public final static int HRV_CONTROL_MODE_REPORT = 0x03;
    /**
     * Hrv Control Bypass Set Command Constant
     */
    public final static int HRV_CONTROL_BYPASS_SET = 0x04;
    /**
     * Hrv Control Bypass Get Command Constant
     */
    public final static int HRV_CONTROL_BYPASS_GET = 0x05;
    /**
     * Hrv Control Bypass Report Command Constant
     */
    public final static int HRV_CONTROL_BYPASS_REPORT = 0x06;
    /**
     * Hrv Control Ventilation Rate Set Command Constant
     */
    public final static int HRV_CONTROL_VENTILATION_RATE_SET = 0x07;
    /**
     * Hrv Control Ventilation Rate Get Command Constant
     */
    public final static int HRV_CONTROL_VENTILATION_RATE_GET = 0x08;
    /**
     * Hrv Control Ventilation Rate Report Command Constant
     */
    public final static int HRV_CONTROL_VENTILATION_RATE_REPORT = 0x09;
    /**
     * Hrv Control Mode Supported Get Command Constant
     */
    public final static int HRV_CONTROL_MODE_SUPPORTED_GET = 0x0A;
    /**
     * Hrv Control Mode Supported Report Command Constant
     */
    public final static int HRV_CONTROL_MODE_SUPPORTED_REPORT = 0x0B;

    /**
     * Creates a new message with the HRV_CONTROL_MODE_SET command.<br>
     * <p>
     * Hrv Control Mode Set<br>
     *
     * @param mode {@link String}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getHrvControlModeSet(String mode) {
        logger.debug("Creating command message HRV_CONTROL_MODE_SET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(HRV_CONTROL_MODE_SET);

        // Process 'Properties1'
        int valmode;
        switch (mode) {
            case "OFF":
                valmode = 0;
                break;
            case "DEMAND_AUTOMATIC":
                valmode = 1;
                break;
            case "SCHEDULE":
                valmode = 2;
                break;
            case "ENERGY_SAVINGS_MODE":
                valmode = 3;
                break;
            case "MANUAL":
                valmode = 4;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for mode: " + mode);
        }
        outputData.write(valmode & 0x1F);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the HRV_CONTROL_MODE_SET command<br>
     * <p>
     * Hrv Control Mode Set<br>
     * <p>
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>MODE {@link String}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleHrvControlModeSet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Properties1'
        switch ((int) payload[2] & 0x1F) {
            case 0x00:
                response.put("MODE", "OFF");
                break;
            case 0x01:
                response.put("MODE", "DEMAND_AUTOMATIC");
                break;
            case 0x02:
                response.put("MODE", "SCHEDULE");
                break;
            case 0x03:
                response.put("MODE", "ENERGY_SAVINGS_MODE");
                break;
            case 0x04:
                response.put("MODE", "MANUAL");
                break;
            default:
                logger.debug("Unknown enum value {} for MODE", String.format("0x%02X", 2));
        }

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the HRV_CONTROL_MODE_GET command.<br>
     * <p>
     * Hrv Control Mode Get<br>
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getHrvControlModeGet() {
        logger.debug("Creating command message HRV_CONTROL_MODE_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(HRV_CONTROL_MODE_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the HRV_CONTROL_MODE_GET command<br>
     * <p>
     * Hrv Control Mode Get<br>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleHrvControlModeGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the HRV_CONTROL_MODE_REPORT command.<br>
     * <p>
     * Hrv Control Mode Report<br>
     *
     * @param mode {@link String}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getHrvControlModeReport(String mode) {
        logger.debug("Creating command message HRV_CONTROL_MODE_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(HRV_CONTROL_MODE_REPORT);

        // Process 'Properties1'
        int valmode;
        switch (mode) {
            case "OFF":
                valmode = 0;
                break;
            case "DEMAND_AUTOMATIC":
                valmode = 1;
                break;
            case "SCHEDULE":
                valmode = 2;
                break;
            case "ENERGY_SAVINGS_MODE":
                valmode = 3;
                break;
            case "MANUAL":
                valmode = 4;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for mode: " + mode);
        }
        outputData.write(valmode & 0x1F);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the HRV_CONTROL_MODE_REPORT command<br>
     * <p>
     * Hrv Control Mode Report<br>
     * <p>
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>MODE {@link String}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleHrvControlModeReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Properties1'
        switch ((int) payload[2] & 0x1F) {
            case 0x00:
                response.put("MODE", "OFF");
                break;
            case 0x01:
                response.put("MODE", "DEMAND_AUTOMATIC");
                break;
            case 0x02:
                response.put("MODE", "SCHEDULE");
                break;
            case 0x03:
                response.put("MODE", "ENERGY_SAVINGS_MODE");
                break;
            case 0x04:
                response.put("MODE", "MANUAL");
                break;
            default:
                logger.debug("Unknown enum value {} for MODE", String.format("0x%02X", 2));
        }

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the HRV_CONTROL_BYPASS_SET command.<br>
     * <p>
     * Hrv Control Bypass Set<br>
     *
     * @param bypass {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getHrvControlBypassSet(Integer bypass) {
        logger.debug("Creating command message HRV_CONTROL_BYPASS_SET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(HRV_CONTROL_BYPASS_SET);

        // Process 'Bypass'
        outputData.write(bypass);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the HRV_CONTROL_BYPASS_SET command<br>
     * <p>
     * Hrv Control Bypass Set<br>
     * <p>
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>BYPASS {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleHrvControlBypassSet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Bypass'
        response.put("BYPASS", Integer.valueOf(payload[2]));

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the HRV_CONTROL_BYPASS_GET command.<br>
     * <p>
     * Hrv Control Bypass Get<br>
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getHrvControlBypassGet() {
        logger.debug("Creating command message HRV_CONTROL_BYPASS_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(HRV_CONTROL_BYPASS_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the HRV_CONTROL_BYPASS_GET command<br>
     * <p>
     * Hrv Control Bypass Get<br>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleHrvControlBypassGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the HRV_CONTROL_BYPASS_REPORT command.<br>
     * <p>
     * Hrv Control Bypass Report<br>
     *
     * @param bypass {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getHrvControlBypassReport(Integer bypass) {
        logger.debug("Creating command message HRV_CONTROL_BYPASS_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(HRV_CONTROL_BYPASS_REPORT);

        // Process 'Bypass'
        outputData.write(bypass);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the HRV_CONTROL_BYPASS_REPORT command<br>
     * <p>
     * Hrv Control Bypass Report<br>
     * <p>
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>BYPASS {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleHrvControlBypassReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Bypass'
        response.put("BYPASS", Integer.valueOf(payload[2]));

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the HRV_CONTROL_VENTILATION_RATE_SET command.<br>
     * <p>
     * Hrv Control Ventilation Rate Set<br>
     *
     * @param ventilationRate {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getHrvControlVentilationRateSet(Integer ventilationRate) {
        logger.debug("Creating command message HRV_CONTROL_VENTILATION_RATE_SET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(HRV_CONTROL_VENTILATION_RATE_SET);

        // Process 'Ventilation Rate'
        outputData.write(ventilationRate);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the HRV_CONTROL_VENTILATION_RATE_SET command<br>
     * <p>
     * Hrv Control Ventilation Rate Set<br>
     * <p>
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>VENTILATION_RATE {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleHrvControlVentilationRateSet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Ventilation Rate'
        response.put("VENTILATION_RATE", Integer.valueOf(payload[2]));

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the HRV_CONTROL_VENTILATION_RATE_GET command.<br>
     * <p>
     * Hrv Control Ventilation Rate Get<br>
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getHrvControlVentilationRateGet() {
        logger.debug("Creating command message HRV_CONTROL_VENTILATION_RATE_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(HRV_CONTROL_VENTILATION_RATE_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the HRV_CONTROL_VENTILATION_RATE_GET command<br>
     * <p>
     * Hrv Control Ventilation Rate Get<br>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleHrvControlVentilationRateGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the HRV_CONTROL_VENTILATION_RATE_REPORT command.<br>
     * <p>
     * Hrv Control Ventilation Rate Report<br>
     *
     * @param ventilationRate {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getHrvControlVentilationRateReport(Integer ventilationRate) {
        logger.debug("Creating command message HRV_CONTROL_VENTILATION_RATE_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(HRV_CONTROL_VENTILATION_RATE_REPORT);

        // Process 'Ventilation Rate'
        outputData.write(ventilationRate);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the HRV_CONTROL_VENTILATION_RATE_REPORT command<br>
     * <p>
     * Hrv Control Ventilation Rate Report<br>
     * <p>
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>VENTILATION_RATE {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleHrvControlVentilationRateReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Ventilation Rate'
        response.put("VENTILATION_RATE", Integer.valueOf(payload[2]));

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the HRV_CONTROL_MODE_SUPPORTED_GET command.<br>
     * <p>
     * Hrv Control Mode Supported Get<br>
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getHrvControlModeSupportedGet() {
        logger.debug("Creating command message HRV_CONTROL_MODE_SUPPORTED_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(HRV_CONTROL_MODE_SUPPORTED_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the HRV_CONTROL_MODE_SUPPORTED_GET command<br>
     * <p>
     * Hrv Control Mode Supported Get<br>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleHrvControlModeSupportedGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the HRV_CONTROL_MODE_SUPPORTED_REPORT command.<br>
     * <p>
     * Hrv Control Mode Supported Report<br>
     *
     * @param manualControlSupported {@link String}
     * @param bitMask {@link List<String>}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getHrvControlModeSupportedReport(String manualControlSupported, List<String> bitMask) {
        logger.debug("Creating command message HRV_CONTROL_MODE_SUPPORTED_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(HRV_CONTROL_MODE_SUPPORTED_REPORT);

        // Process 'Properties1'
        int valmanualControlSupported;
        switch (manualControlSupported) {
            case "BYPASS_OPEN_CLOSE":
                valmanualControlSupported = 0;
                break;
            case "BYPASS_AUTO":
                valmanualControlSupported = 1;
                break;
            case "MODULATED_BYPASS":
                valmanualControlSupported = 2;
                break;
            case "VENTILATION_RATE":
                valmanualControlSupported = 3;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for manualControlSupported: " + manualControlSupported);
        }
        outputData.write(valmanualControlSupported & 0x0F);

        // Process 'Bit Mask'
        int valBitMask = 0;
        valBitMask |= (bitMask.contains("OFF") ? 0x01 : 0);
        valBitMask |= (bitMask.contains("DEMAND_AUTOMATIC") ? 0x02 : 0);
        valBitMask |= (bitMask.contains("SCHEDULE") ? 0x04 : 0);
        valBitMask |= (bitMask.contains("ENERGY_SAVINGS_MODE") ? 0x08 : 0);
        valBitMask |= (bitMask.contains("MANUAL") ? 0x10 : 0);
        outputData.write(valBitMask);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the HRV_CONTROL_MODE_SUPPORTED_REPORT command<br>
     * <p>
     * Hrv Control Mode Supported Report<br>
     * <p>
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>MANUAL_CONTROL_SUPPORTED {@link String}
     * <li>BIT_MASK {@link List}<{@link String}>
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleHrvControlModeSupportedReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Properties1'
        switch ((int) payload[2] & 0x0F) {
            case 0x00:
                response.put("MANUAL_CONTROL_SUPPORTED", "BYPASS_OPEN_CLOSE");
                break;
            case 0x01:
                response.put("MANUAL_CONTROL_SUPPORTED", "BYPASS_AUTO");
                break;
            case 0x02:
                response.put("MANUAL_CONTROL_SUPPORTED", "MODULATED_BYPASS");
                break;
            case 0x03:
                response.put("MANUAL_CONTROL_SUPPORTED", "VENTILATION_RATE");
                break;
            default:
                logger.debug("Unknown enum value {} for MANUAL_CONTROL_SUPPORTED", String.format("0x%02X", 2));
        }

        // Process 'Bit Mask'
        List<String> responseBitMask = new ArrayList<String>();
        int cntBitMask = 0;
        while (cntBitMask < payload.length - 3) {
            if ((payload[3 + (cntBitMask / 8)] & cntBitMask % 8) == 0) {
                continue;
            }
            switch (cntBitMask) {
                case 0x00:
                    responseBitMask.add("OFF");
                    break;
                case 0x01:
                    responseBitMask.add("DEMAND_AUTOMATIC");
                    break;
                case 0x02:
                    responseBitMask.add("SCHEDULE");
                    break;
                case 0x03:
                    responseBitMask.add("ENERGY_SAVINGS_MODE");
                    break;
                case 0x04:
                    responseBitMask.add("MANUAL");
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
