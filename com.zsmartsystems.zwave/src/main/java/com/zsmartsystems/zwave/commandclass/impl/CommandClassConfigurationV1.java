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
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_CONFIGURATION</b> version <b>1</b>.
 * <p>
 * Command Class Configuration
 * <p>
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassConfigurationV1 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassConfigurationV1.class);

    /**
     * Integer command class key for COMMAND_CLASS_CONFIGURATION
     */
    public final static int COMMAND_CLASS_KEY = 0x70;

    /**
     * Configuration Set Command Constant
     */
    public final static int CONFIGURATION_SET = 0x04;
    /**
     * Configuration Get Command Constant
     */
    public final static int CONFIGURATION_GET = 0x05;
    /**
     * Configuration Report Command Constant
     */
    public final static int CONFIGURATION_REPORT = 0x06;


    /**
     * Map holding constants for Level
     */
    private static Map<Integer, String> constantLevel = new HashMap<Integer, String>();
    static {

        // Constants for Level
        constantLevel.put(0x80, "DEFAULT");
    }

    /**
     * Creates a new message with the CONFIGURATION_SET command.
     * <p>
     * Configuration Set
     *
     * @param parameterNumber {@link Integer}
     * @param defaultValue {@link Boolean}
     * @param configurationValue {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getConfigurationSet(Integer parameterNumber, Boolean defaultValue, byte[] configurationValue) {
        logger.debug("Creating command message CONFIGURATION_SET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(CONFIGURATION_SET);

        // Process 'Parameter Number'
        outputData.write(parameterNumber);

        // Process 'Level'
        // Size is used by 'Configuration Value'
        int size = configurationValue.length;
        int valLevel = 0;
        valLevel |= size & 0x07;
        valLevel |= defaultValue ? 0x80 : 0;
        outputData.write(valLevel);

        // Process 'Configuration Value'
        if (configurationValue != null) {
            try {
                outputData.write(configurationValue);
            } catch (IOException e) {
            }
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the CONFIGURATION_SET command.
     * <p>
     * Configuration Set
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>PARAMETER_NUMBER {@link Integer}
     * <li>DEFAULT {@link Boolean}
     * <li>CONFIGURATION_VALUE {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleConfigurationSet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Parameter Number'
        response.put("PARAMETER_NUMBER", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Level'
        // Size is used by 'Configuration Value'
        int varSize = payload[msgOffset] & 0x07;
        response.put("DEFAULT", Boolean.valueOf((payload[msgOffset] & 0x80) != 0));
        msgOffset += 1;

        // Process 'Configuration Value'
        ByteArrayOutputStream valConfigurationValue = new ByteArrayOutputStream();
        for (int cntConfigurationValue = 0; cntConfigurationValue < varSize; cntConfigurationValue++) {
            valConfigurationValue.write(payload[msgOffset + cntConfigurationValue]);
        }
        response.put("CONFIGURATION_VALUE", valConfigurationValue.toByteArray());
        msgOffset += varSize;

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the CONFIGURATION_GET command.
     * <p>
     * Configuration Get
     *
     * @param parameterNumber {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getConfigurationGet(Integer parameterNumber) {
        logger.debug("Creating command message CONFIGURATION_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(CONFIGURATION_GET);

        // Process 'Parameter Number'
        outputData.write(parameterNumber);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the CONFIGURATION_GET command.
     * <p>
     * Configuration Get
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>PARAMETER_NUMBER {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleConfigurationGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Parameter Number'
        response.put("PARAMETER_NUMBER", Integer.valueOf(payload[2]));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the CONFIGURATION_REPORT command.
     * <p>
     * Configuration Report
     *
     * @param parameterNumber {@link Integer}
     * @param configurationValue {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getConfigurationReport(Integer parameterNumber, byte[] configurationValue) {
        logger.debug("Creating command message CONFIGURATION_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(CONFIGURATION_REPORT);

        // Process 'Parameter Number'
        outputData.write(parameterNumber);

        // Process 'Level'
        // Size is used by 'Configuration Value'
        int size = configurationValue.length;
        outputData.write(size & 0x07);

        // Process 'Configuration Value'
        if (configurationValue != null) {
            try {
                outputData.write(configurationValue);
            } catch (IOException e) {
            }
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the CONFIGURATION_REPORT command.
     * <p>
     * Configuration Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>PARAMETER_NUMBER {@link Integer}
     * <li>CONFIGURATION_VALUE {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleConfigurationReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Parameter Number'
        response.put("PARAMETER_NUMBER", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Level'
        // Size is used by 'Configuration Value'
        int varSize = payload[msgOffset] & 0x07;
        msgOffset += 1;

        // Process 'Configuration Value'
        ByteArrayOutputStream valConfigurationValue = new ByteArrayOutputStream();
        for (int cntConfigurationValue = 0; cntConfigurationValue < varSize; cntConfigurationValue++) {
            valConfigurationValue.write(payload[msgOffset + cntConfigurationValue]);
        }
        response.put("CONFIGURATION_VALUE", valConfigurationValue.toByteArray());
        msgOffset += varSize;

        // Return the map of processed response data;
        return response;
    }

}
