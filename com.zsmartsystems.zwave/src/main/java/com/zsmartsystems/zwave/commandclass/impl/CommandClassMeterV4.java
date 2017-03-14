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
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_METER</b> version <b>4</b>.<br>
 *
 * Command Class Meter<br>
 *
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.<br>
 *
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassMeterV4 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassMeterV4.class);

    /**
     * Integer command class key for COMMAND_CLASS_METER
     */
    public final static int COMMAND_CLASS_KEY = 0x32;

    /**
     * Meter Get Command Constant
     */
    public final static int METER_GET = 0x01;
    /**
     * Meter Report Command Constant
     */
    public final static int METER_REPORT = 0x02;
    /**
     * Meter Supported Get Command Constant
     */
    public final static int METER_SUPPORTED_GET = 0x03;
    /**
     * Meter Supported Report Command Constant
     */
    public final static int METER_SUPPORTED_REPORT = 0x04;
    /**
     * Meter Reset Command Constant
     */
    public final static int METER_RESET = 0x05;

    /**
     * Creates a new message with the METER_GET command.<br>
     *
     * Meter Get<br>
     *
     *
     * @param scale {@link Integer}
     * @param rateType {@link String}
     * @param scale2 {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMeterGet(Integer scale, String rateType, Integer scale2) {
        logger.debug("Creating command message METER_GET version 4");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(METER_GET);

        // Process 'Properties1'
        int valProperties1 = 0;
        valProperties1 |= ((scale << 3) & 0x38);
        int valrateType;
        switch (rateType) {
            case "IMPORT":
                valrateType = 1;
                break;
            case "EXPORT":
                valrateType = 2;
                break;
            case "NOT_TO_BE_USED":
                valrateType = 3;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for rateType: " + rateType);
        }
        valProperties1 |= valrateType >> 6 & 0xC0;
        outputData.write(valProperties1);

        // Process 'Scale 2'
        outputData.write(scale2);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the METER_GET command<br>
     *
     * Meter Get<br>
     *
     *
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>SCALE {@link Integer}
     * <li>RATE_TYPE {@link String}
     * <li>SCALE_2 {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMeterGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Properties1'
        response.put("SCALE", new Integer((payload[2] & 0x38 >> 3)));
        switch ((payload[2] & 0xC0) >> 6) {
            case 0x01:
                response.put("RATE_TYPE", "IMPORT");
                break;
            case 0x02:
                response.put("RATE_TYPE", "EXPORT");
                break;
            case 0x03:
                response.put("RATE_TYPE", "NOT_TO_BE_USED");
                break;
            default:
                logger.debug("Unknown enum value {} for RATE_TYPE", String.format("0x%02X", 2));
        }

        // Process 'Scale 2'
        response.put("SCALE_2", new Integer(payload[3]));

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the METER_REPORT command.<br>
     *
     * Meter Report<br>
     *
     *
     * @param meterType {@link String}
     * @param rateType {@link String}
     * @param scaleBit2 {@link Boolean}
     * @param size {@link Integer}
     * @param scaleBits10 {@link Integer}
     * @param precision {@link Integer}
     * @param meterValue {@link byte[]}
     * @param deltaTime {@link Integer}
     * @param previousMeterValue {@link byte[]}
     * @param scale2 {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMeterReport(String meterType, String rateType, Boolean scaleBit2, Integer size,
            Integer scaleBits10, Integer precision, byte[] meterValue, Integer deltaTime, byte[] previousMeterValue,
            Integer scale2) {
        logger.debug("Creating command message METER_REPORT version 4");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(METER_REPORT);

        // Process 'Properties1'
        int valProperties1 = 0;
        int valmeterType;
        switch (meterType) {
            case "ELECTRIC_METER":
                valmeterType = 1;
                break;
            case "GAS_METER":
                valmeterType = 2;
                break;
            case "WATER_METER":
                valmeterType = 3;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for meterType: " + meterType);
        }
        valProperties1 |= valmeterType & 0x1F;
        int valrateType;
        switch (rateType) {
            case "IMPORT":
                valrateType = 1;
                break;
            case "EXPORT":
                valrateType = 2;
                break;
            case "NOT_TO_BE_USED":
                valrateType = 3;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for rateType: " + rateType);
        }
        valProperties1 |= valrateType >> 5 & 0x60;
        valProperties1 |= scaleBit2 ? 0x80 : 0;
        outputData.write(valProperties1);

        // Process 'Properties2'
        int valProperties2 = 0;
        valProperties2 |= size & 0x07;
        valProperties2 |= ((scaleBits10 << 3) & 0x18);
        valProperties2 |= ((precision << 5) & 0xE0);
        outputData.write(valProperties2);

        // Process 'Meter Value'
        try {
            outputData.write(meterValue);
        } catch (IOException e) {
        }

        // Process 'Delta Time'
        outputData.write((deltaTime >> 8) & 0xff);
        outputData.write(deltaTime & 0xff);

        // Process 'Previous Meter Value'
        try {
            outputData.write(previousMeterValue);
        } catch (IOException e) {
        }

        // Process 'Scale 2'
        outputData.write(scale2);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the METER_REPORT command<br>
     *
     * Meter Report<br>
     *
     *
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>METER_TYPE {@link String}
     * <li>RATE_TYPE {@link String}
     * <li>SCALE_BIT_2 {@link Boolean}
     * <li>SIZE {@link Integer}
     * <li>SCALE_BITS_10 {@link Integer}
     * <li>PRECISION {@link Integer}
     * <li>METER_VALUE {@link byte[]}
     * <li>DELTA_TIME {@link Integer}
     * <li>PREVIOUS_METER_VALUE {@link byte[]}
     * <li>SCALE_2 {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMeterReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Properties1'
        switch ((int) payload[msgOffset] & 0x1F) {
            case 0x01:
                response.put("METER_TYPE", "ELECTRIC_METER");
                break;
            case 0x02:
                response.put("METER_TYPE", "GAS_METER");
                break;
            case 0x03:
                response.put("METER_TYPE", "WATER_METER");
                break;
            default:
                logger.debug("Unknown enum value {} for METER_TYPE", String.format("0x%02X", msgOffset));
        }
        switch ((payload[msgOffset] & 0x60) >> 5) {
            case 0x01:
                response.put("RATE_TYPE", "IMPORT");
                break;
            case 0x02:
                response.put("RATE_TYPE", "EXPORT");
                break;
            case 0x03:
                response.put("RATE_TYPE", "NOT_TO_BE_USED");
                break;
            default:
                logger.debug("Unknown enum value {} for RATE_TYPE", String.format("0x%02X", msgOffset));
        }
        response.put("SCALE_BIT_2", new Boolean((payload[msgOffset] & 0x80) != 0));
        msgOffset += 1;

        // Process 'Properties2'
        response.put("SIZE", new Integer(payload[msgOffset] & 0x07));
        response.put("SCALE_BITS_10", new Integer((payload[msgOffset] & 0x18 >> 3)));
        response.put("PRECISION", new Integer((payload[msgOffset] & 0xE0 >> 5)));
        msgOffset += 1;

        // Process 'Meter Value'
        int valMeterValue = 0;
        int lenMeterValue = payload[msgOffset - 1] & 0x07;
        for (int cntMeterValue = 0; cntMeterValue < lenMeterValue; cntMeterValue++) {
            valMeterValue = (valMeterValue << 8) + payload[msgOffset + cntMeterValue];
        }
        response.put("METER_VALUE", valMeterValue);
        msgOffset += lenMeterValue;

        // Process 'Delta Time'
        response.put("DELTA_TIME", new Integer(payload[msgOffset] << 8 + payload[msgOffset + 12]));
        msgOffset += 2;

        // Process 'Previous Meter Value'
        int valPreviousMeterValue = 0;
        int lenPreviousMeterValue = payload[msgOffset - 3] & 0x07;
        for (int cntPreviousMeterValue = 0; cntPreviousMeterValue < lenPreviousMeterValue; cntPreviousMeterValue++) {
            valPreviousMeterValue = (valPreviousMeterValue << 8) + payload[msgOffset + cntPreviousMeterValue];
        }
        response.put("PREVIOUS_METER_VALUE", valPreviousMeterValue);
        msgOffset += lenPreviousMeterValue;

        // Process 'Scale 2'
        response.put("SCALE_2", new Integer(payload[msgOffset]));
        msgOffset += 1;

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the METER_SUPPORTED_GET command.<br>
     *
     * Meter Supported Get<br>
     *
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMeterSupportedGet() {
        logger.debug("Creating command message METER_SUPPORTED_GET version 4");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(METER_SUPPORTED_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the METER_SUPPORTED_GET command<br>
     *
     * Meter Supported Get<br>
     *
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMeterSupportedGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the METER_SUPPORTED_REPORT command.<br>
     *
     * Meter Supported Report<br>
     *
     *
     * @param meterType {@link String}
     * @param rateType {@link String}
     * @param meterReset {@link Boolean}
     * @param scaleSupported0 {@link Integer}
     * @param mST {@link Boolean}
     * @param numberOfScaleSupportedBytesToFollow {@link Integer}
     * @param scaleSupported {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMeterSupportedReport(String meterType, String rateType, Boolean meterReset,
            Integer scaleSupported0, Boolean mST, Integer numberOfScaleSupportedBytesToFollow, byte[] scaleSupported) {
        logger.debug("Creating command message METER_SUPPORTED_REPORT version 4");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(METER_SUPPORTED_REPORT);

        // Process 'Properties1'
        int valProperties1 = 0;
        int valmeterType;
        switch (meterType) {
            case "ELECTRIC_METER":
                valmeterType = 1;
                break;
            case "GAS_METER":
                valmeterType = 2;
                break;
            case "WATER_METER":
                valmeterType = 3;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for meterType: " + meterType);
        }
        valProperties1 |= valmeterType & 0x1F;
        int valrateType;
        switch (rateType) {
            case "IMPORT_ONLY":
                valrateType = 1;
                break;
            case "EXPORT_ONLY":
                valrateType = 2;
                break;
            case "IMPORT_AND_EXPORT":
                valrateType = 3;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for rateType: " + rateType);
        }
        valProperties1 |= valrateType >> 5 & 0x60;
        valProperties1 |= meterReset ? 0x80 : 0;
        outputData.write(valProperties1);

        // Process 'Properties2'
        int valProperties2 = 0;
        valProperties2 |= scaleSupported0 & 0x7F;
        valProperties2 |= mST ? 0x80 : 0;
        outputData.write(valProperties2);

        // Process 'Number of Scale Supported Bytes to Follow'
        outputData.write(numberOfScaleSupportedBytesToFollow);

        // Process 'Scale Supported'
        try {
            outputData.write(scaleSupported);
        } catch (IOException e) {
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the METER_SUPPORTED_REPORT command<br>
     *
     * Meter Supported Report<br>
     *
     *
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>METER_TYPE {@link String}
     * <li>RATE_TYPE {@link String}
     * <li>METER_RESET {@link Boolean}
     * <li>SCALE_SUPPORTED_0 {@link Integer}
     * <li>M_S_T {@link Boolean}
     * <li>NUMBER_OF_SCALE_SUPPORTED_BYTES_TO_FOLLOW {@link Integer}
     * <li>SCALE_SUPPORTED {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMeterSupportedReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Properties1'
        switch ((int) payload[msgOffset] & 0x1F) {
            case 0x01:
                response.put("METER_TYPE", "ELECTRIC_METER");
                break;
            case 0x02:
                response.put("METER_TYPE", "GAS_METER");
                break;
            case 0x03:
                response.put("METER_TYPE", "WATER_METER");
                break;
            default:
                logger.debug("Unknown enum value {} for METER_TYPE", String.format("0x%02X", msgOffset));
        }
        switch ((payload[msgOffset] & 0x60) >> 5) {
            case 0x01:
                response.put("RATE_TYPE", "IMPORT_ONLY");
                break;
            case 0x02:
                response.put("RATE_TYPE", "EXPORT_ONLY");
                break;
            case 0x03:
                response.put("RATE_TYPE", "IMPORT_AND_EXPORT");
                break;
            default:
                logger.debug("Unknown enum value {} for RATE_TYPE", String.format("0x%02X", msgOffset));
        }
        response.put("METER_RESET", new Boolean((payload[msgOffset] & 0x80) != 0));
        msgOffset += 1;

        // Process 'Properties2'
        response.put("SCALE_SUPPORTED_0", new Integer(payload[msgOffset] & 0x7F));
        response.put("M_S_T", new Boolean((payload[msgOffset] & 0x80) != 0));
        msgOffset += 1;

        // Process 'Number of Scale Supported Bytes to Follow'
        response.put("NUMBER_OF_SCALE_SUPPORTED_BYTES_TO_FOLLOW", new Integer(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Scale Supported'
        int valScaleSupported = 0;
        int lenScaleSupported = payload[msgOffset - 1];
        for (int cntScaleSupported = 0; cntScaleSupported < lenScaleSupported; cntScaleSupported++) {
            valScaleSupported = (valScaleSupported << 8) + payload[msgOffset + cntScaleSupported];
        }
        response.put("SCALE_SUPPORTED", valScaleSupported);
        msgOffset += lenScaleSupported;

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the METER_RESET command.<br>
     *
     * Meter Reset<br>
     *
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMeterReset() {
        logger.debug("Creating command message METER_RESET version 4");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(METER_RESET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the METER_RESET command<br>
     *
     * Meter Reset<br>
     *
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMeterReset(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }

}