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
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_MULTI_CHANNEL_ASSOCIATION</b> version <b>3</b>.
 * <p>
 * Command Class Multi Channel Association
 * <p>
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.
 * <p>
 * Command class key is 0x8E.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassMultiChannelAssociationV3 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassMultiChannelAssociationV3.class);

    /**
     * Integer command class key for COMMAND_CLASS_MULTI_CHANNEL_ASSOCIATION
     */
    public final static int COMMAND_CLASS_KEY = 0x8E;

    /**
     * Multi Channel Association Set Command Constant
     */
    public final static int MULTI_CHANNEL_ASSOCIATION_SET = 0x01;
    /**
     * Multi Channel Association Get Command Constant
     */
    public final static int MULTI_CHANNEL_ASSOCIATION_GET = 0x02;
    /**
     * Multi Channel Association Report Command Constant
     */
    public final static int MULTI_CHANNEL_ASSOCIATION_REPORT = 0x03;
    /**
     * Multi Channel Association Remove Command Constant
     */
    public final static int MULTI_CHANNEL_ASSOCIATION_REMOVE = 0x04;
    /**
     * Multi Channel Association Groupings Get Command Constant
     */
    public final static int MULTI_CHANNEL_ASSOCIATION_GROUPINGS_GET = 0x05;
    /**
     * Multi Channel Association Groupings Report Command Constant
     */
    public final static int MULTI_CHANNEL_ASSOCIATION_GROUPINGS_REPORT = 0x06;


    /**
     * Map holding constants for MultiChannelAssociationRemoveProperties1
     */
    private static Map<Integer, String> constantMultiChannelAssociationRemoveProperties1 = new HashMap<Integer, String>();

    /**
     * Map holding constants for MultiChannelAssociationReportProperties1
     */
    private static Map<Integer, String> constantMultiChannelAssociationReportProperties1 = new HashMap<Integer, String>();

    /**
     * Map holding constants for MultiChannelAssociationReportMarker
     */
    private static Map<Integer, String> constantMultiChannelAssociationReportMarker = new HashMap<Integer, String>();

    /**
     * Map holding constants for MultiChannelAssociationSetProperties1
     */
    private static Map<Integer, String> constantMultiChannelAssociationSetProperties1 = new HashMap<Integer, String>();

    /**
     * Map holding constants for MultiChannelAssociationRemoveMarker
     */
    private static Map<Integer, String> constantMultiChannelAssociationRemoveMarker = new HashMap<Integer, String>();

    /**
     * Map holding constants for MultiChannelAssociationSetMarker
     */
    private static Map<Integer, String> constantMultiChannelAssociationSetMarker = new HashMap<Integer, String>();
    static {

        // Constants for MultiChannelAssociationRemoveProperties1
        constantMultiChannelAssociationRemoveProperties1.put(0x80, "BIT_ADDRESS");

        // Constants for MultiChannelAssociationReportProperties1
        constantMultiChannelAssociationReportProperties1.put(0x80, "BIT_ADDRESS");

        // Constants for MultiChannelAssociationReportMarker
        constantMultiChannelAssociationReportMarker.put(0x00, "MARKER");

        // Constants for MultiChannelAssociationSetProperties1
        constantMultiChannelAssociationSetProperties1.put(0x80, "BIT_ADDRESS");

        // Constants for MultiChannelAssociationRemoveMarker
        constantMultiChannelAssociationRemoveMarker.put(0x00, "MARKER");

        // Constants for MultiChannelAssociationSetMarker
        constantMultiChannelAssociationSetMarker.put(0x00, "MARKER");
    }

    /**
     * Creates a new message with the MULTI_CHANNEL_ASSOCIATION_SET command.
     * <p>
     * Multi Channel Association Set
     *
     * @param groupingIdentifier {@link Integer}
     * @param nodeId {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMultiChannelAssociationSet(Integer groupingIdentifier, byte[] nodeId) {
        logger.debug("Creating command message MULTI_CHANNEL_ASSOCIATION_SET version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(MULTI_CHANNEL_ASSOCIATION_SET);

        // Process 'Grouping Identifier'
        outputData.write(groupingIdentifier);

        // Process 'Node ID'
        if (nodeId != null) {
            try {
                outputData.write(nodeId);
            } catch (IOException e) {
            }
        }

        // Process 'Marker'
        outputData.write(0x00);

        // Process 'vg'

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the MULTI_CHANNEL_ASSOCIATION_SET command.
     * <p>
     * Multi Channel Association Set
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>GROUPING_IDENTIFIER {@link Integer}
     * <li>NODE_ID {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMultiChannelAssociationSet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Grouping Identifier'
        response.put("GROUPING_IDENTIFIER", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Node ID'
        ByteArrayOutputStream valNodeId = new ByteArrayOutputStream();
        while (msgOffset < payload.length) {
            // Detect the marker
            if ((payload[msgOffset] & 0xFF) == 0x00) {
                break;
            }
            valNodeId.write(payload[msgOffset]);
            msgOffset++;
        }
        response.put("NODE_ID", valNodeId);

        // Process 'Marker'
        // Adjust position to account for the marker
        msgOffset += 1;

        // Process 'vg'

        // Create a list to hold the group vg
        List<Map<String, Object>> variantList = new ArrayList<Map<String, Object>>();

        int offset = msgOffset;
        while (offset < payload.length) {
            // Create a map to hold the members of this variant
            Map<String, Object> variant = new HashMap<String, Object>();

            // Process 'Multi Channel Node ID'
            variant.put("MULTI_CHANNEL_NODE_ID", Integer.valueOf(payload[msgOffset]));
            msgOffset += 1;

            // Process 'Properties1'
            variant.put("END_POINT", Integer.valueOf(payload[msgOffset] & 0x7F));
            variant.put("BIT_ADDRESS", Boolean.valueOf((payload[msgOffset] & 0x80) != 0));
            msgOffset += 1;

        }

        // Add the variant list to the response
        response.put("VG", variantList);
        msgOffset += 1;

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the MULTI_CHANNEL_ASSOCIATION_GET command.
     * <p>
     * Multi Channel Association Get
     *
     * @param groupingIdentifier {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMultiChannelAssociationGet(Integer groupingIdentifier) {
        logger.debug("Creating command message MULTI_CHANNEL_ASSOCIATION_GET version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(MULTI_CHANNEL_ASSOCIATION_GET);

        // Process 'Grouping Identifier'
        outputData.write(groupingIdentifier);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the MULTI_CHANNEL_ASSOCIATION_GET command.
     * <p>
     * Multi Channel Association Get
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
    public static Map<String, Object> handleMultiChannelAssociationGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Grouping Identifier'
        response.put("GROUPING_IDENTIFIER", Integer.valueOf(payload[2]));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the MULTI_CHANNEL_ASSOCIATION_REPORT command.
     * <p>
     * Multi Channel Association Report
     *
     * @param groupingIdentifier {@link Integer}
     * @param maxNodesSupported {@link Integer}
     * @param reportsToFollow {@link Integer}
     * @param nodeId {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMultiChannelAssociationReport(Integer groupingIdentifier, Integer maxNodesSupported,
            Integer reportsToFollow, byte[] nodeId) {
        logger.debug("Creating command message MULTI_CHANNEL_ASSOCIATION_REPORT version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(MULTI_CHANNEL_ASSOCIATION_REPORT);

        // Process 'Grouping Identifier'
        outputData.write(groupingIdentifier);

        // Process 'Max Nodes Supported'
        outputData.write(maxNodesSupported);

        // Process 'Reports to Follow'
        outputData.write(reportsToFollow);

        // Process 'Node ID'
        if (nodeId != null) {
            try {
                outputData.write(nodeId);
            } catch (IOException e) {
            }
        }

        // Process 'Marker'
        outputData.write(0x00);

        // Process 'vg'

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the MULTI_CHANNEL_ASSOCIATION_REPORT command.
     * <p>
     * Multi Channel Association Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>GROUPING_IDENTIFIER {@link Integer}
     * <li>MAX_NODES_SUPPORTED {@link Integer}
     * <li>REPORTS_TO_FOLLOW {@link Integer}
     * <li>NODE_ID {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMultiChannelAssociationReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Grouping Identifier'
        response.put("GROUPING_IDENTIFIER", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Max Nodes Supported'
        response.put("MAX_NODES_SUPPORTED", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Reports to Follow'
        response.put("REPORTS_TO_FOLLOW", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Node ID'
        ByteArrayOutputStream valNodeId = new ByteArrayOutputStream();
        while (msgOffset < payload.length) {
            // Detect the marker
            if ((payload[msgOffset] & 0xFF) == 0x00) {
                break;
            }
            valNodeId.write(payload[msgOffset]);
            msgOffset++;
        }
        response.put("NODE_ID", valNodeId);

        // Process 'Marker'
        // Adjust position to account for the marker
        msgOffset += 1;

        // Process 'vg'

        // Create a list to hold the group vg
        List<Map<String, Object>> variantList = new ArrayList<Map<String, Object>>();

        int offset = msgOffset;
        while (offset < payload.length) {
            // Create a map to hold the members of this variant
            Map<String, Object> variant = new HashMap<String, Object>();

            // Process 'Multi Channel Node ID'
            variant.put("MULTI_CHANNEL_NODE_ID", Integer.valueOf(payload[msgOffset]));
            msgOffset += 1;

            // Process 'Properties1'
            variant.put("END_POINT", Integer.valueOf(payload[msgOffset] & 0x7F));
            variant.put("BIT_ADDRESS", Boolean.valueOf((payload[msgOffset] & 0x80) != 0));
            msgOffset += 1;

        }

        // Add the variant list to the response
        response.put("VG", variantList);
        msgOffset += 1;

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the MULTI_CHANNEL_ASSOCIATION_REMOVE command.
     * <p>
     * Multi Channel Association Remove
     *
     * @param groupingIdentifier {@link Integer}
     * @param nodeId {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMultiChannelAssociationRemove(Integer groupingIdentifier, byte[] nodeId) {
        logger.debug("Creating command message MULTI_CHANNEL_ASSOCIATION_REMOVE version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(MULTI_CHANNEL_ASSOCIATION_REMOVE);

        // Process 'Grouping Identifier'
        outputData.write(groupingIdentifier);

        // Process 'Node ID'
        if (nodeId != null) {
            try {
                outputData.write(nodeId);
            } catch (IOException e) {
            }
        }

        // Process 'Marker'
        outputData.write(0x00);

        // Process 'vg'

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the MULTI_CHANNEL_ASSOCIATION_REMOVE command.
     * <p>
     * Multi Channel Association Remove
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>GROUPING_IDENTIFIER {@link Integer}
     * <li>NODE_ID {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMultiChannelAssociationRemove(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Grouping Identifier'
        response.put("GROUPING_IDENTIFIER", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Node ID'
        ByteArrayOutputStream valNodeId = new ByteArrayOutputStream();
        while (msgOffset < payload.length) {
            // Detect the marker
            if ((payload[msgOffset] & 0xFF) == 0x00) {
                break;
            }
            valNodeId.write(payload[msgOffset]);
            msgOffset++;
        }
        response.put("NODE_ID", valNodeId);

        // Process 'Marker'
        // Adjust position to account for the marker
        msgOffset += 1;

        // Process 'vg'

        // Create a list to hold the group vg
        List<Map<String, Object>> variantList = new ArrayList<Map<String, Object>>();

        int offset = msgOffset;
        while (offset < payload.length) {
            // Create a map to hold the members of this variant
            Map<String, Object> variant = new HashMap<String, Object>();

            // Process 'Multi Channel Node ID'
            variant.put("MULTI_CHANNEL_NODE_ID", Integer.valueOf(payload[msgOffset]));
            msgOffset += 1;

            // Process 'Properties1'
            variant.put("END_POINT", Integer.valueOf(payload[msgOffset] & 0x7F));
            variant.put("BIT_ADDRESS", Boolean.valueOf((payload[msgOffset] & 0x80) != 0));
            msgOffset += 1;

        }

        // Add the variant list to the response
        response.put("VG", variantList);
        msgOffset += 1;

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the MULTI_CHANNEL_ASSOCIATION_GROUPINGS_GET command.
     * <p>
     * Multi Channel Association Groupings Get
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMultiChannelAssociationGroupingsGet() {
        logger.debug("Creating command message MULTI_CHANNEL_ASSOCIATION_GROUPINGS_GET version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(MULTI_CHANNEL_ASSOCIATION_GROUPINGS_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the MULTI_CHANNEL_ASSOCIATION_GROUPINGS_GET command.
     * <p>
     * Multi Channel Association Groupings Get
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMultiChannelAssociationGroupingsGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the MULTI_CHANNEL_ASSOCIATION_GROUPINGS_REPORT command.
     * <p>
     * Multi Channel Association Groupings Report
     *
     * @param supportedGroupings {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getMultiChannelAssociationGroupingsReport(Integer supportedGroupings) {
        logger.debug("Creating command message MULTI_CHANNEL_ASSOCIATION_GROUPINGS_REPORT version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(MULTI_CHANNEL_ASSOCIATION_GROUPINGS_REPORT);

        // Process 'Supported Groupings'
        outputData.write(supportedGroupings);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the MULTI_CHANNEL_ASSOCIATION_GROUPINGS_REPORT command.
     * <p>
     * Multi Channel Association Groupings Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>SUPPORTED_GROUPINGS {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleMultiChannelAssociationGroupingsReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Supported Groupings'
        response.put("SUPPORTED_GROUPINGS", Integer.valueOf(payload[2]));

        // Return the map of processed response data;
        return response;
    }

}
