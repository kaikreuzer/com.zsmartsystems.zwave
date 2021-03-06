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
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_ASSOCIATION_GRP_INFO</b> version <b>3</b>.
 * <p>
 * Command Class Association Group Info
 * <p>
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.
 * <p>
 * Command class key is 0x59.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassAssociationGrpInfoV3 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassAssociationGrpInfoV3.class);

    /**
     * Integer command class key for COMMAND_CLASS_ASSOCIATION_GRP_INFO
     */
    public final static int COMMAND_CLASS_KEY = 0x59;

    /**
     * Association Group Name Get Command Constant
     */
    public final static int ASSOCIATION_GROUP_NAME_GET = 0x01;
    /**
     * Association Group Name Report Command Constant
     */
    public final static int ASSOCIATION_GROUP_NAME_REPORT = 0x02;
    /**
     * Association Group Info Get Command Constant
     */
    public final static int ASSOCIATION_GROUP_INFO_GET = 0x03;
    /**
     * Association Group Info Report Command Constant
     */
    public final static int ASSOCIATION_GROUP_INFO_REPORT = 0x04;
    /**
     * Association Group Command List Get Command Constant
     */
    public final static int ASSOCIATION_GROUP_COMMAND_LIST_GET = 0x05;
    /**
     * Association Group Command List Report Command Constant
     */
    public final static int ASSOCIATION_GROUP_COMMAND_LIST_REPORT = 0x06;



    /**
     * Map holding constants for AssociationGroupInfoReportProfile1
     */
    private static Map<Integer, String> constantAssociationGroupInfoReportProfile1 = new HashMap<Integer, String>();

    /**
     * Map holding constants for AssociationGroupInfoReportProperties1
     */
    private static Map<Integer, String> constantAssociationGroupInfoReportProperties1 = new HashMap<Integer, String>();

    /**
     * Map holding constants for AssociationGroupCommandListGetProperties1
     */
    private static Map<Integer, String> constantAssociationGroupCommandListGetProperties1 = new HashMap<Integer, String>();

    /**
     * Map holding constants for AssociationGroupInfoGetProperties1
     */
    private static Map<Integer, String> constantAssociationGroupInfoGetProperties1 = new HashMap<Integer, String>();
    static {

        // Constants for AssociationGroupInfoReportProfile1
        constantAssociationGroupInfoReportProfile1.put(0x00, "PROFILE_GENERAL");
        constantAssociationGroupInfoReportProfile1.put(0x32, "PROFILE_METER");
        constantAssociationGroupInfoReportProfile1.put(0x31, "PROFILE_SENSOR");
        constantAssociationGroupInfoReportProfile1.put(0x20, "PROFILE_CONTROL");
        constantAssociationGroupInfoReportProfile1.put(0x71, "PROFILE_NOTIFICATION");
        constantAssociationGroupInfoReportProfile1.put(0x6B, "PROFILE_IRRIGATION");

        // Constants for AssociationGroupInfoReportProperties1
        constantAssociationGroupInfoReportProperties1.put(0x40, "DYNAMIC_INFO");
        constantAssociationGroupInfoReportProperties1.put(0x80, "LIST_MODE");

        // Constants for AssociationGroupCommandListGetProperties1
        constantAssociationGroupCommandListGetProperties1.put(0x80, "ALLOW_CACHE");

        // Constants for AssociationGroupInfoGetProperties1
        constantAssociationGroupInfoGetProperties1.put(0x40, "LIST_MODE");
        constantAssociationGroupInfoGetProperties1.put(0x80, "REFRESH_CACHE");
    }

    /**
     * Creates a new message with the ASSOCIATION_GROUP_NAME_GET command.
     * <p>
     * Association Group Name Get
     *
     * @param groupingIdentifier {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getAssociationGroupNameGet(Integer groupingIdentifier) {
        logger.debug("Creating command message ASSOCIATION_GROUP_NAME_GET version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(ASSOCIATION_GROUP_NAME_GET);

        // Process 'Grouping Identifier'
        outputData.write(groupingIdentifier);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the ASSOCIATION_GROUP_NAME_GET command.
     * <p>
     * Association Group Name Get
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
    public static Map<String, Object> handleAssociationGroupNameGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Grouping Identifier'
        response.put("GROUPING_IDENTIFIER", Integer.valueOf(payload[2]));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the ASSOCIATION_GROUP_NAME_REPORT command.
     * <p>
     * Association Group Name Report
     *
     * @param groupingIdentifier {@link Integer}
     * @param lengthOfName {@link Integer}
     * @param name {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getAssociationGroupNameReport(Integer groupingIdentifier, Integer lengthOfName, byte[] name) {
        logger.debug("Creating command message ASSOCIATION_GROUP_NAME_REPORT version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(ASSOCIATION_GROUP_NAME_REPORT);

        // Process 'Grouping Identifier'
        outputData.write(groupingIdentifier);

        // Process 'Length of Name'
        outputData.write(lengthOfName);

        // Process 'Name'
        if (name != null) {
            try {
                outputData.write(name);
            } catch (IOException e) {
            }
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the ASSOCIATION_GROUP_NAME_REPORT command.
     * <p>
     * Association Group Name Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>GROUPING_IDENTIFIER {@link Integer}
     * <li>LENGTH_OF_NAME {@link Integer}
     * <li>NAME {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleAssociationGroupNameReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Grouping Identifier'
        response.put("GROUPING_IDENTIFIER", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Length of Name'
        response.put("LENGTH_OF_NAME", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Name'
        StringBuilder valName = new StringBuilder();
        int lenName = payload[msgOffset - 1];
        for (int cntName = 0; cntName < lenName; cntName++) {
            valName.append(payload[msgOffset + cntName]);
        }
        response.put("NAME", valName.toString());
        msgOffset += lenName;

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the ASSOCIATION_GROUP_INFO_GET command.
     * <p>
     * Association Group Info Get
     *
     * @param listMode {@link Boolean}
     * @param refreshCache {@link Boolean}
     * @param groupingIdentifier {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getAssociationGroupInfoGet(Boolean listMode, Boolean refreshCache, Integer groupingIdentifier) {
        logger.debug("Creating command message ASSOCIATION_GROUP_INFO_GET version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(ASSOCIATION_GROUP_INFO_GET);

        // Process 'Properties1'
        int valProperties1 = 0;
        valProperties1 |= listMode ? 0x40 : 0;
        valProperties1 |= refreshCache ? 0x80 : 0;
        outputData.write(valProperties1);

        // Process 'Grouping Identifier'
        outputData.write(groupingIdentifier);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the ASSOCIATION_GROUP_INFO_GET command.
     * <p>
     * Association Group Info Get
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>LIST_MODE {@link Boolean}
     * <li>REFRESH_CACHE {@link Boolean}
     * <li>GROUPING_IDENTIFIER {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleAssociationGroupInfoGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Properties1'
        response.put("LIST_MODE", Boolean.valueOf((payload[2] & 0x40) != 0));
        response.put("REFRESH_CACHE", Boolean.valueOf((payload[2] & 0x80) != 0));

        // Process 'Grouping Identifier'
        response.put("GROUPING_IDENTIFIER", Integer.valueOf(payload[3]));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the ASSOCIATION_GROUP_INFO_REPORT command.
     * <p>
     * Association Group Info Report
     *
     * @param groupCount {@link Integer}
     * @param dynamicInfo {@link Boolean}
     * @param listMode {@link Boolean}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getAssociationGroupInfoReport(Integer groupCount, Boolean dynamicInfo, Boolean listMode) {
        logger.debug("Creating command message ASSOCIATION_GROUP_INFO_REPORT version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(ASSOCIATION_GROUP_INFO_REPORT);

        // Process 'Properties1'
        int valProperties1 = 0;
        valProperties1 |= groupCount & 0x3F;
        valProperties1 |= dynamicInfo ? 0x40 : 0;
        valProperties1 |= listMode ? 0x80 : 0;
        outputData.write(valProperties1);

        // Process 'vg1'

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the ASSOCIATION_GROUP_INFO_REPORT command.
     * <p>
     * Association Group Info Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>GROUP_COUNT {@link Integer}
     * <li>DYNAMIC_INFO {@link Boolean}
     * <li>LIST_MODE {@link Boolean}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleAssociationGroupInfoReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Properties1'
        response.put("GROUP_COUNT", Integer.valueOf(payload[2] & 0x3F));
        response.put("DYNAMIC_INFO", Boolean.valueOf((payload[2] & 0x40) != 0));
        response.put("LIST_MODE", Boolean.valueOf((payload[2] & 0x80) != 0));

        // Process 'vg1'

        // Create a list to hold the group vg1
        List<Map<String, Object>> variantList = new ArrayList<Map<String, Object>>();

        int offset = 3;
        while (offset < payload.length) {
            // Create a map to hold the members of this variant
            Map<String, Object> variant = new HashMap<String, Object>();

            // Process 'Grouping Identifier'
            variant.put("GROUPING_IDENTIFIER", Integer.valueOf(payload[3]));

            // Process 'Mode'
            variant.put("MODE", Integer.valueOf(payload[4]));

            // Process 'Profile1'
            variant.put("PROFILE1", constantAssociationGroupInfoReportProfile1.get(payload[5] & 0xff));

            // Process 'Profile2'

            // Process 'Event Code'
            variant.put("EVENT_CODE", Integer.valueOf(((payload[8] & 0xff) << 8) + (payload[9] & 0xff)));

            // Add to the list
            variantList.add(variant);

            // Add the length of this variant
            offset += (payload[offset + 0] & 0x3F) >> 0;
        }

        // Add the variant list to the response
        response.put("VG1", variantList);

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the ASSOCIATION_GROUP_COMMAND_LIST_GET command.
     * <p>
     * Association Group Command List Get
     *
     * @param allowCache {@link Boolean}
     * @param groupingIdentifier {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getAssociationGroupCommandListGet(Boolean allowCache, Integer groupingIdentifier) {
        logger.debug("Creating command message ASSOCIATION_GROUP_COMMAND_LIST_GET version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(ASSOCIATION_GROUP_COMMAND_LIST_GET);

        // Process 'Properties1'
        outputData.write(allowCache ? 0x80 : 0);

        // Process 'Grouping Identifier'
        outputData.write(groupingIdentifier);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the ASSOCIATION_GROUP_COMMAND_LIST_GET command.
     * <p>
     * Association Group Command List Get
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>ALLOW_CACHE {@link Boolean}
     * <li>GROUPING_IDENTIFIER {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleAssociationGroupCommandListGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Properties1'
        response.put("ALLOW_CACHE", Boolean.valueOf((payload[2] & 0x80) != 0));

        // Process 'Grouping Identifier'
        response.put("GROUPING_IDENTIFIER", Integer.valueOf(payload[3]));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the ASSOCIATION_GROUP_COMMAND_LIST_REPORT command.
     * <p>
     * Association Group Command List Report
     *
     * @param groupingIdentifier {@link Integer}
     * @param listLength {@link Integer}
     * @param command {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getAssociationGroupCommandListReport(Integer groupingIdentifier, Integer listLength,
            byte[] command) {
        logger.debug("Creating command message ASSOCIATION_GROUP_COMMAND_LIST_REPORT version 3");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(ASSOCIATION_GROUP_COMMAND_LIST_REPORT);

        // Process 'Grouping Identifier'
        outputData.write(groupingIdentifier);

        // Process 'List Length'
        outputData.write(listLength);

        // Process 'Command'
        if (command != null) {
            try {
                outputData.write(command);
            } catch (IOException e) {
            }
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the ASSOCIATION_GROUP_COMMAND_LIST_REPORT command.
     * <p>
     * Association Group Command List Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>GROUPING_IDENTIFIER {@link Integer}
     * <li>LIST_LENGTH {@link Integer}
     * <li>COMMAND {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleAssociationGroupCommandListReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Grouping Identifier'
        response.put("GROUPING_IDENTIFIER", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'List Length'
        response.put("LIST_LENGTH", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Command'
        ByteArrayOutputStream valCommand = new ByteArrayOutputStream();
        int lenCommand = payload[msgOffset - 1];
        for (int cntCommand = 0; cntCommand < lenCommand; cntCommand++) {
            valCommand.write(payload[msgOffset + cntCommand]);
        }
        response.put("COMMAND", valCommand.toByteArray());
        msgOffset += lenCommand;

        // Return the map of processed response data;
        return response;
    }

}
