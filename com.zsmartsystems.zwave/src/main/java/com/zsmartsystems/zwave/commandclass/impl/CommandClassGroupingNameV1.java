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
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_GROUPING_NAME</b> version <b>1</b>.<br>
 *
 * Command Class Grouping Name<br>
 *
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.<br>
 *
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
     * Creates a new message with the GROUPING_NAME_SET command.<br>
     *
     * Grouping Name Set<br>
     *
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
        try {
            outputData.write(groupingName);
        } catch (IOException e) {
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the GROUPING_NAME_SET command<br>
     *
     * Grouping Name Set<br>
     *
     *
     * The output data {@link Map} has the following properties<br>
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
        response.put("GROUPING_IDENTIFIER", new Integer(payload[2]));

        // Process 'Properties1'
        response.put("CHAR_PRESENTATION", new Integer(payload[3] & 0x07));

        // Process 'Grouping Name'
        byte[] valGroupingName = new byte[16];
        for (int cntGroupingName = 0; cntGroupingName < 16; cntGroupingName++) {
            valGroupingName[cntGroupingName] = payload[4 + cntGroupingName];
        }
        response.put("GROUPING_NAME", valGroupingName);

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the GROUPING_NAME_GET command.<br>
     *
     * Grouping Name Get<br>
     *
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
     * Processes a received frame with the GROUPING_NAME_GET command<br>
     *
     * Grouping Name Get<br>
     *
     *
     * The output data {@link Map} has the following properties<br>
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
        response.put("GROUPING_IDENTIFIER", new Integer(payload[2]));

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the GROUPING_NAME_REPORT command.<br>
     *
     * Grouping Name Report<br>
     *
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
        try {
            outputData.write(groupingName);
        } catch (IOException e) {
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the GROUPING_NAME_REPORT command<br>
     *
     * Grouping Name Report<br>
     *
     *
     * The output data {@link Map} has the following properties<br>
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
        response.put("GROUPING_IDENTIFIER", new Integer(payload[2]));

        // Process 'Properties1'
        response.put("CHAR_PRESENTATION", new Integer(payload[3] & 0x07));

        // Process 'Grouping Name'
        byte[] valGroupingName = new byte[16];
        for (int cntGroupingName = 0; cntGroupingName < 16; cntGroupingName++) {
            valGroupingName[cntGroupingName] = payload[4 + cntGroupingName];
        }
        response.put("GROUPING_NAME", valGroupingName);

        // Return the map of processed response data;
        return response;
    }

}