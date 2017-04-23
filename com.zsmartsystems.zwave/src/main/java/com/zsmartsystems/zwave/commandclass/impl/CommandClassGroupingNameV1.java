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
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_GROUPING_NAME</b> version <b>1</b>.
 * <p>
 * Command Class Grouping Name
 * <p>
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassGroupingNameV1 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassGroupingNameV1.class);

    /**
     * Integer command class key for COMMAND_CLASS_GROUPING_NAME
     */
    public final static int COMMAND_CLASS_KEY = 0x7B;

    /**
     * Grouping Name Set Command Constant
     */
    public final static int GROUPING_NAME_SET = 0x01;
    /**
     * Grouping Name Get Command Constant
     */
    public final static int GROUPING_NAME_GET = 0x02;
    /**
     * Grouping Name Report Command Constant
     */
    public final static int GROUPING_NAME_REPORT = 0x03;


    /**
     * Creates a new message with the GROUPING_NAME_SET command.
     * <p>
     * Grouping Name Set
     *
     * @param groupingIdentifier {@link Integer}
     * @param charPresentation {@link Integer}
     * @param groupingName {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getGroupingNameSet(Integer groupingIdentifier, Integer charPresentation, byte[] groupingName) {
        logger.debug("Creating command message GROUPING_NAME_SET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(GROUPING_NAME_SET);

        // Process 'Grouping identifier'
        outputData.write(groupingIdentifier);

        // Process 'Properties1'
        outputData.write(charPresentation & 0x07);

        // Process 'Grouping Name'
        if (groupingName != null) {
            if (groupingName.length > 16) {
                throw new IllegalArgumentException("Length of array groupingName exceeds maximum length of 16 bytes");
            }
            try {
                outputData.write(groupingName);
            } catch (IOException e) {
            }
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the GROUPING_NAME_SET command.
     * <p>
     * Grouping Name Set
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>GROUPING_IDENTIFIER {@link Integer}
     * <li>CHAR_PRESENTATION {@link Integer}
     * <li>GROUPING_NAME {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleGroupingNameSet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Grouping identifier'
        response.put("GROUPING_IDENTIFIER", Integer.valueOf(payload[2]));

        // Process 'Properties1'
        response.put("CHAR_PRESENTATION", Integer.valueOf(payload[3] & 0x07));

        // Process 'Grouping Name'
        int lenGroupingName = Math.min(16, payload.length - 4);
        byte[] valGroupingName = new byte[lenGroupingName];
        for (int cntGroupingName = 0; cntGroupingName < lenGroupingName; cntGroupingName++) {
            valGroupingName[cntGroupingName] = payload[4 + cntGroupingName];
        }
        response.put("GROUPING_NAME", valGroupingName);

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the GROUPING_NAME_GET command.
     * <p>
     * Grouping Name Get
     *
     * @param groupingIdentifier {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getGroupingNameGet(Integer groupingIdentifier) {
        logger.debug("Creating command message GROUPING_NAME_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(GROUPING_NAME_GET);

        // Process 'Grouping identifier'
        outputData.write(groupingIdentifier);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the GROUPING_NAME_GET command.
     * <p>
     * Grouping Name Get
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>GROUPING_IDENTIFIER {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleGroupingNameGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Grouping identifier'
        response.put("GROUPING_IDENTIFIER", Integer.valueOf(payload[2]));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the GROUPING_NAME_REPORT command.
     * <p>
     * Grouping Name Report
     *
     * @param groupingIdentifier {@link Integer}
     * @param charPresentation {@link Integer}
     * @param groupingName {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getGroupingNameReport(Integer groupingIdentifier, Integer charPresentation, byte[] groupingName) {
        logger.debug("Creating command message GROUPING_NAME_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(GROUPING_NAME_REPORT);

        // Process 'Grouping identifier'
        outputData.write(groupingIdentifier);

        // Process 'Properties1'
        outputData.write(charPresentation & 0x07);

        // Process 'Grouping Name'
        if (groupingName != null) {
            if (groupingName.length > 16) {
                throw new IllegalArgumentException("Length of array groupingName exceeds maximum length of 16 bytes");
            }
            try {
                outputData.write(groupingName);
            } catch (IOException e) {
            }
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the GROUPING_NAME_REPORT command.
     * <p>
     * Grouping Name Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>GROUPING_IDENTIFIER {@link Integer}
     * <li>CHAR_PRESENTATION {@link Integer}
     * <li>GROUPING_NAME {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleGroupingNameReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Grouping identifier'
        response.put("GROUPING_IDENTIFIER", Integer.valueOf(payload[2]));

        // Process 'Properties1'
        response.put("CHAR_PRESENTATION", Integer.valueOf(payload[3] & 0x07));

        // Process 'Grouping Name'
        int lenGroupingName = Math.min(16, payload.length - 4);
        byte[] valGroupingName = new byte[lenGroupingName];
        for (int cntGroupingName = 0; cntGroupingName < lenGroupingName; cntGroupingName++) {
            valGroupingName[cntGroupingName] = payload[4 + cntGroupingName];
        }
        response.put("GROUPING_NAME", valGroupingName);

        // Return the map of processed response data;
        return response;
    }

}
