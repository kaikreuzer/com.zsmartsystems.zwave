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
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_SCHEDULE</b> version <b>1</b>.
 * <p>
 * Command Class Schedule
 * <p>
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassScheduleV1 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassScheduleV1.class);

    /**
     * Integer command class key for COMMAND_CLASS_SCHEDULE
     */
    public final static int COMMAND_CLASS_KEY = 0x53;

    /**
     * Schedule Support Get Command Constant
     */
    public final static int SCHEDULE_SUPPORTED_GET = 0x01;
    /**
     * Schedule Support Report Command Constant
     */
    public final static int SCHEDULE_SUPPORTED_REPORT = 0x02;
    /**
     * Schedule Set Command Constant
     */
    public final static int COMMAND_SCHEDULE_SET = 0x03;
    /**
     * Schedule Get Command Constant
     */
    public final static int COMMAND_SCHEDULE_GET = 0x04;
    /**
     * Schedule Report Command Constant
     */
    public final static int COMMAND_SCHEDULE_REPORT = 0x05;
    /**
     * Schedule Remove Command Constant
     */
    public final static int SCHEDULE_REMOVE = 0x06;
    /**
     * Schedule State Set Command Constant
     */
    public final static int SCHEDULE_STATE_SET = 0x07;
    /**
     * Schedule State Get Command Constant
     */
    public final static int SCHEDULE_STATE_GET = 0x08;
    /**
     * Schedule State Report Command Constant
     */
    public final static int SCHEDULE_STATE_REPORT = 0x09;


    /**
     * Map holding constants for Properties1
     */
    private static Map<Integer, String> constantProperties1 = new HashMap<Integer, String>();

    /**
     * Map holding constants for Properties3
     */
    private static Map<Integer, String> constantProperties3 = new HashMap<Integer, String>();
    static {

        // Constants for Properties1
        constantProperties1.put(0x40, "FALLBACK_SUPPORT");
        constantProperties1.put(0x80, "SUPPORT_ENABLE_DISABLE");

        // Constants for Properties3
        constantProperties3.put(0x80, "OVERRIDE_SUPPORT");
    }

    /**
     * Creates a new message with the SCHEDULE_SUPPORTED_GET command.
     * <p>
     * Schedule Support Get
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getScheduleSupportedGet() {
        logger.debug("Creating command message SCHEDULE_SUPPORTED_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(SCHEDULE_SUPPORTED_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the SCHEDULE_SUPPORTED_GET command.
     * <p>
     * Schedule Support Get
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleScheduleSupportedGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the SCHEDULE_SUPPORTED_REPORT command.
     * <p>
     * Schedule Support Report
     *
     * @param numberOfSupportedScheduleId {@link Integer}
     * @param startTimeSupport {@link Integer}
     * @param fallbackSupport {@link Boolean}
     * @param supportEnableDisable {@link Boolean}
     * @param numberOfSupportedCc {@link Integer}
     * @param supportedOverrideTypes {@link Integer}
     * @param overrideSupport {@link Boolean}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getScheduleSupportedReport(Integer numberOfSupportedScheduleId, Integer startTimeSupport,
            Boolean fallbackSupport, Boolean supportEnableDisable, Integer numberOfSupportedCc,
            Integer supportedOverrideTypes, Boolean overrideSupport) {
        logger.debug("Creating command message SCHEDULE_SUPPORTED_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(SCHEDULE_SUPPORTED_REPORT);

        // Process 'Number of Supported Schedule ID'
        outputData.write(numberOfSupportedScheduleId);

        // Process 'Properties1'
        int valProperties1 = 0;
        valProperties1 |= startTimeSupport & 0x3F;
        valProperties1 |= fallbackSupport ? 0x40 : 0;
        valProperties1 |= supportEnableDisable ? 0x80 : 0;
        outputData.write(valProperties1);

        // Process 'Number of supported CC'
        outputData.write(numberOfSupportedCc);

        // Process 'vg1'

        // Process 'Properties3'
        int valProperties3 = 0;
        valProperties3 |= supportedOverrideTypes & 0x7F;
        valProperties3 |= overrideSupport ? 0x80 : 0;
        outputData.write(valProperties3);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the SCHEDULE_SUPPORTED_REPORT command.
     * <p>
     * Schedule Support Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>NUMBER_OF_SUPPORTED_SCHEDULE_ID {@link Integer}
     * <li>START_TIME_SUPPORT {@link Integer}
     * <li>FALLBACK_SUPPORT {@link Boolean}
     * <li>SUPPORT_ENABLE_DISABLE {@link Boolean}
     * <li>NUMBER_OF_SUPPORTED_CC {@link Integer}
     * <li>SUPPORTED_OVERRIDE_TYPES {@link Integer}
     * <li>OVERRIDE_SUPPORT {@link Boolean}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleScheduleSupportedReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Number of Supported Schedule ID'
        response.put("NUMBER_OF_SUPPORTED_SCHEDULE_ID", Integer.valueOf(payload[2]));

        // Process 'Properties1'
        response.put("START_TIME_SUPPORT", Integer.valueOf(payload[3] & 0x3F));
        response.put("FALLBACK_SUPPORT", Boolean.valueOf((payload[3] & 0x40) != 0));
        response.put("SUPPORT_ENABLE_DISABLE", Boolean.valueOf((payload[3] & 0x80) != 0));

        // Process 'Number of supported CC'
        response.put("NUMBER_OF_SUPPORTED_CC", Integer.valueOf(payload[4]));

        // Process 'vg1'

        // Create a list to hold the group vg1
        List<Map<String, Object>> variantList = new ArrayList<Map<String, Object>>();

        int offset = 5;
        while (offset < payload.length) {
            // Create a map to hold the members of this variant
            Map<String, Object> variant = new HashMap<String, Object>();

            // Process 'Supported CC'
            variant.put("SUPPORTED_CC", Integer.valueOf(payload[5]));

            // Process 'Properties2'
            variant.put("SUPPORTED_COMMAND", Integer.valueOf(payload[6] & 0x03));

            // Add to the list
            variantList.add(variant);

            // Add the length of this variant
            offset += (payload[offset + 2] & 0xFF) >> 0;
        }

        // Add the variant list to the response
        response.put("VG1", variantList);

        // Process 'Properties3'
        response.put("SUPPORTED_OVERRIDE_TYPES", Integer.valueOf(payload[7] & 0x7F));
        response.put("OVERRIDE_SUPPORT", Boolean.valueOf((payload[7] & 0x80) != 0));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the COMMAND_SCHEDULE_SET command.
     * <p>
     * Schedule Set
     *
     * @param scheduleId {@link Integer}
     * @param userIdentifier {@link Integer}
     * @param startYear {@link Integer}
     * @param startMonth {@link Integer}
     * @param startDayOfMonth {@link Integer}
     * @param startWeekday {@link Integer}
     * @param res {@link Boolean}
     * @param startHour {@link Integer}
     * @param durationType {@link Integer}
     * @param startMinute {@link Integer}
     * @param durationByte {@link Integer}
     * @param reportsToFollow {@link Integer}
     * @param numberOfCmdToFollow {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getCommandScheduleSet(Integer scheduleId, Integer userIdentifier, Integer startYear,
            Integer startMonth, Integer startDayOfMonth, Integer startWeekday, Boolean res, Integer startHour,
            Integer durationType, Integer startMinute, Integer durationByte, Integer reportsToFollow,
            Integer numberOfCmdToFollow) {
        logger.debug("Creating command message COMMAND_SCHEDULE_SET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(COMMAND_SCHEDULE_SET);

        // Process 'Schedule ID'
        outputData.write(scheduleId);

        // Process 'User Identifier'
        outputData.write(userIdentifier);

        // Process 'Start Year'
        outputData.write(startYear);

        // Process 'Properties1'
        outputData.write(startMonth & 0x0F);

        // Process 'Properties2'
        outputData.write(startDayOfMonth & 0x1F);

        // Process 'Properties3'
        int valProperties3 = 0;
        valProperties3 |= startWeekday & 0x7F;
        valProperties3 |= res ? 0x80 : 0;
        outputData.write(valProperties3);

        // Process 'Properties4'
        int valProperties4 = 0;
        valProperties4 |= startHour & 0x1F;
        valProperties4 |= ((durationType << 5) & 0xE0);
        outputData.write(valProperties4);

        // Process 'Properties5'
        outputData.write(startMinute & 0x3F);

        // Process 'Duration Byte'
        outputData.write((durationByte >> 8) & 0xff);
        outputData.write(durationByte & 0xff);

        // Process 'Reports to Follow'
        outputData.write(reportsToFollow);

        // Process 'Number of Cmd to Follow'
        outputData.write(numberOfCmdToFollow);

        // Process 'vg1'

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the COMMAND_SCHEDULE_SET command.
     * <p>
     * Schedule Set
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>SCHEDULE_ID {@link Integer}
     * <li>USER_IDENTIFIER {@link Integer}
     * <li>START_YEAR {@link Integer}
     * <li>START_MONTH {@link Integer}
     * <li>START_DAY_OF_MONTH {@link Integer}
     * <li>START_WEEKDAY {@link Integer}
     * <li>RES_ {@link Boolean}
     * <li>START_HOUR {@link Integer}
     * <li>DURATION_TYPE {@link Integer}
     * <li>START_MINUTE {@link Integer}
     * <li>DURATION_BYTE {@link Integer}
     * <li>REPORTS_TO_FOLLOW {@link Integer}
     * <li>NUMBER_OF_CMD_TO_FOLLOW {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleCommandScheduleSet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Schedule ID'
        response.put("SCHEDULE_ID", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'User Identifier'
        response.put("USER_IDENTIFIER", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Start Year'
        response.put("START_YEAR", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Properties1'
        response.put("START_MONTH", Integer.valueOf(payload[msgOffset] & 0x0F));
        msgOffset += 1;

        // Process 'Properties2'
        response.put("START_DAY_OF_MONTH", Integer.valueOf(payload[msgOffset] & 0x1F));
        msgOffset += 1;

        // Process 'Properties3'
        response.put("START_WEEKDAY", Integer.valueOf(payload[msgOffset] & 0x7F));
        response.put("RES_", Boolean.valueOf((payload[msgOffset] & 0x80) != 0));
        msgOffset += 1;

        // Process 'Properties4'
        response.put("START_HOUR", Integer.valueOf(payload[msgOffset] & 0x1F));
        response.put("DURATION_TYPE", Integer.valueOf(payload[msgOffset] & 0xE0 >> 5));
        msgOffset += 1;

        // Process 'Properties5'
        response.put("START_MINUTE", Integer.valueOf(payload[msgOffset] & 0x3F));
        msgOffset += 1;

        // Process 'Duration Byte'
        response.put("DURATION_BYTE", Integer.valueOf(((payload[msgOffset] & 0xff) << 8) + (payload[msgOffset + 1] & 0xff)));
        msgOffset += 2;

        // Process 'Reports to Follow'
        response.put("REPORTS_TO_FOLLOW", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Number of Cmd to Follow'
        response.put("NUMBER_OF_CMD_TO_FOLLOW", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'vg1'

        // Create a list to hold the group vg1
        List<Map<String, Object>> variantList = new ArrayList<Map<String, Object>>();

        int offset = msgOffset;
        while (offset < payload.length) {
            // Create a map to hold the members of this variant
            Map<String, Object> variant = new HashMap<String, Object>();

            // Process 'Cmd Length'
            variant.put("CMD_LENGTH", Integer.valueOf(payload[msgOffset]));
            msgOffset += 1;

            // Process 'Cmd Byte'
            variant.put("CMD_BYTE", Integer.valueOf(payload[msgOffset]));
            msgOffset += payload[msgOffset - 1];

            // Add to the list
            variantList.add(variant);

            // Add the length of this variant
            offset += (payload[offset + 10] & 0xFF) >> 0;
        }

        // Add the variant list to the response
        response.put("VG1", variantList);

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the COMMAND_SCHEDULE_GET command.
     * <p>
     * Schedule Get
     *
     * @param scheduleId {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getCommandScheduleGet(Integer scheduleId) {
        logger.debug("Creating command message COMMAND_SCHEDULE_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(COMMAND_SCHEDULE_GET);

        // Process 'Schedule ID'
        outputData.write(scheduleId);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the COMMAND_SCHEDULE_GET command.
     * <p>
     * Schedule Get
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>SCHEDULE_ID {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleCommandScheduleGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Schedule ID'
        response.put("SCHEDULE_ID", Integer.valueOf(payload[2]));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the COMMAND_SCHEDULE_REPORT command.
     * <p>
     * Schedule Report
     *
     * @param scheduleId {@link Integer}
     * @param userIdentifier {@link Integer}
     * @param startYear {@link Integer}
     * @param startMonth {@link Integer}
     * @param activeId {@link Integer}
     * @param startDayOfMonth {@link Integer}
     * @param startWeekday {@link Integer}
     * @param res {@link Boolean}
     * @param startHour {@link Integer}
     * @param durationType {@link Integer}
     * @param startMinute {@link Integer}
     * @param durationByte {@link Integer}
     * @param reportsToFollow {@link Integer}
     * @param numberOfCmdToFollow {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getCommandScheduleReport(Integer scheduleId, Integer userIdentifier, Integer startYear,
            Integer startMonth, Integer activeId, Integer startDayOfMonth, Integer startWeekday, Boolean res,
            Integer startHour, Integer durationType, Integer startMinute, Integer durationByte, Integer reportsToFollow,
            Integer numberOfCmdToFollow) {
        logger.debug("Creating command message COMMAND_SCHEDULE_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(COMMAND_SCHEDULE_REPORT);

        // Process 'Schedule ID'
        outputData.write(scheduleId);

        // Process 'User Identifier'
        outputData.write(userIdentifier);

        // Process 'Start Year'
        outputData.write(startYear);

        // Process 'Properties1'
        int valProperties1 = 0;
        valProperties1 |= startMonth & 0x0F;
        valProperties1 |= ((activeId << 4) & 0xF0);
        outputData.write(valProperties1);

        // Process 'Properties2'
        outputData.write(startDayOfMonth & 0x1F);

        // Process 'Properties3'
        int valProperties3 = 0;
        valProperties3 |= startWeekday & 0x7F;
        valProperties3 |= res ? 0x80 : 0;
        outputData.write(valProperties3);

        // Process 'Properties4'
        int valProperties4 = 0;
        valProperties4 |= startHour & 0x1F;
        valProperties4 |= ((durationType << 5) & 0xE0);
        outputData.write(valProperties4);

        // Process 'Properties5'
        outputData.write(startMinute & 0x3F);

        // Process 'Duration Byte'
        outputData.write((durationByte >> 8) & 0xff);
        outputData.write(durationByte & 0xff);

        // Process 'Reports to Follow'
        outputData.write(reportsToFollow);

        // Process 'Number of Cmd to Follow'
        outputData.write(numberOfCmdToFollow);

        // Process 'vg1'

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the COMMAND_SCHEDULE_REPORT command.
     * <p>
     * Schedule Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>SCHEDULE_ID {@link Integer}
     * <li>USER_IDENTIFIER {@link Integer}
     * <li>START_YEAR {@link Integer}
     * <li>START_MONTH {@link Integer}
     * <li>ACTIVE_ID {@link Integer}
     * <li>START_DAY_OF_MONTH {@link Integer}
     * <li>START_WEEKDAY {@link Integer}
     * <li>RES_ {@link Boolean}
     * <li>START_HOUR {@link Integer}
     * <li>DURATION_TYPE {@link Integer}
     * <li>START_MINUTE {@link Integer}
     * <li>DURATION_BYTE {@link Integer}
     * <li>REPORTS_TO_FOLLOW {@link Integer}
     * <li>NUMBER_OF_CMD_TO_FOLLOW {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleCommandScheduleReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Schedule ID'
        response.put("SCHEDULE_ID", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'User Identifier'
        response.put("USER_IDENTIFIER", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Start Year'
        response.put("START_YEAR", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Properties1'
        response.put("START_MONTH", Integer.valueOf(payload[msgOffset] & 0x0F));
        response.put("ACTIVE_ID", Integer.valueOf(payload[msgOffset] & 0xF0 >> 4));
        msgOffset += 1;

        // Process 'Properties2'
        response.put("START_DAY_OF_MONTH", Integer.valueOf(payload[msgOffset] & 0x1F));
        msgOffset += 1;

        // Process 'Properties3'
        response.put("START_WEEKDAY", Integer.valueOf(payload[msgOffset] & 0x7F));
        response.put("RES_", Boolean.valueOf((payload[msgOffset] & 0x80) != 0));
        msgOffset += 1;

        // Process 'Properties4'
        response.put("START_HOUR", Integer.valueOf(payload[msgOffset] & 0x1F));
        response.put("DURATION_TYPE", Integer.valueOf(payload[msgOffset] & 0xE0 >> 5));
        msgOffset += 1;

        // Process 'Properties5'
        response.put("START_MINUTE", Integer.valueOf(payload[msgOffset] & 0x3F));
        msgOffset += 1;

        // Process 'Duration Byte'
        response.put("DURATION_BYTE", Integer.valueOf(((payload[msgOffset] & 0xff) << 8) + (payload[msgOffset + 1] & 0xff)));
        msgOffset += 2;

        // Process 'Reports to Follow'
        response.put("REPORTS_TO_FOLLOW", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Number of Cmd to Follow'
        response.put("NUMBER_OF_CMD_TO_FOLLOW", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'vg1'

        // Create a list to hold the group vg1
        List<Map<String, Object>> variantList = new ArrayList<Map<String, Object>>();

        int offset = msgOffset;
        while (offset < payload.length) {
            // Create a map to hold the members of this variant
            Map<String, Object> variant = new HashMap<String, Object>();

            // Process 'Cmd Length'
            variant.put("CMD_LENGTH", Integer.valueOf(payload[msgOffset]));
            msgOffset += 1;

            // Process 'Cmd Byte'
            variant.put("CMD_BYTE", Integer.valueOf(payload[msgOffset]));
            msgOffset += payload[msgOffset - 1];

            // Add to the list
            variantList.add(variant);

            // Add the length of this variant
            offset += (payload[offset + 10] & 0xFF) >> 0;
        }

        // Add the variant list to the response
        response.put("VG1", variantList);

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the SCHEDULE_REMOVE command.
     * <p>
     * Schedule Remove
     *
     * @param scheduleId {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getScheduleRemove(Integer scheduleId) {
        logger.debug("Creating command message SCHEDULE_REMOVE version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(SCHEDULE_REMOVE);

        // Process 'Schedule ID'
        outputData.write(scheduleId);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the SCHEDULE_REMOVE command.
     * <p>
     * Schedule Remove
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>SCHEDULE_ID {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleScheduleRemove(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Schedule ID'
        response.put("SCHEDULE_ID", Integer.valueOf(payload[2]));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the SCHEDULE_STATE_SET command.
     * <p>
     * Schedule State Set
     *
     * @param scheduleId {@link Integer}
     * @param scheduleState {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getScheduleStateSet(Integer scheduleId, Integer scheduleState) {
        logger.debug("Creating command message SCHEDULE_STATE_SET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(SCHEDULE_STATE_SET);

        // Process 'Schedule ID'
        outputData.write(scheduleId);

        // Process 'Schedule State'
        outputData.write(scheduleState);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the SCHEDULE_STATE_SET command.
     * <p>
     * Schedule State Set
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>SCHEDULE_ID {@link Integer}
     * <li>SCHEDULE_STATE {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleScheduleStateSet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Schedule ID'
        response.put("SCHEDULE_ID", Integer.valueOf(payload[2]));

        // Process 'Schedule State'
        response.put("SCHEDULE_STATE", Integer.valueOf(payload[3]));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the SCHEDULE_STATE_GET command.
     * <p>
     * Schedule State Get
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getScheduleStateGet() {
        logger.debug("Creating command message SCHEDULE_STATE_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(SCHEDULE_STATE_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the SCHEDULE_STATE_GET command.
     * <p>
     * Schedule State Get
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleScheduleStateGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the SCHEDULE_STATE_REPORT command.
     * <p>
     * Schedule State Report
     *
     * @param numberOfSupportedScheduleId {@link Integer}
     * @param override {@link Boolean}
     * @param reportsToFollow {@link Integer}
     * @param activeId1 {@link Integer}
     * @param activeId2 {@link Integer}
     * @param activeId3 {@link Integer}
     * @param activeIdN {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getScheduleStateReport(Integer numberOfSupportedScheduleId, Boolean override,
            Integer reportsToFollow, Integer activeId1, Integer activeId2, Integer activeId3, Integer activeIdN) {
        logger.debug("Creating command message SCHEDULE_STATE_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(SCHEDULE_STATE_REPORT);

        // Process 'Number of Supported Schedule ID'
        outputData.write(numberOfSupportedScheduleId);

        // Process 'Properties1'
        int valProperties1 = 0;
        valProperties1 |= override ? 0x01 : 0;
        valProperties1 |= ((reportsToFollow << 1) & 0xFE);
        outputData.write(valProperties1);

        // Process 'Properties2'
        int valProperties2 = 0;
        valProperties2 |= activeId1 & 0x0F;
        valProperties2 |= ((activeId2 << 4) & 0xF0);
        outputData.write(valProperties2);

        // Process 'Properties3'
        int valProperties3 = 0;
        valProperties3 |= activeId3 & 0x0F;
        valProperties3 |= ((activeIdN << 4) & 0xF0);
        outputData.write(valProperties3);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the SCHEDULE_STATE_REPORT command.
     * <p>
     * Schedule State Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>NUMBER_OF_SUPPORTED_SCHEDULE_ID {@link Integer}
     * <li>OVERRIDE {@link Boolean}
     * <li>REPORTS_TO_FOLLOW {@link Integer}
     * <li>ACTIVE_ID_1 {@link Integer}
     * <li>ACTIVE_ID_2 {@link Integer}
     * <li>ACTIVE_ID_3 {@link Integer}
     * <li>ACTIVE_ID_N {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleScheduleStateReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Number of Supported Schedule ID'
        response.put("NUMBER_OF_SUPPORTED_SCHEDULE_ID", Integer.valueOf(payload[2]));

        // Process 'Properties1'
        response.put("OVERRIDE", Boolean.valueOf((payload[3] & 0x01) != 0));
        response.put("REPORTS_TO_FOLLOW", Integer.valueOf(payload[3] & 0xFE >> 1));

        // Process 'Properties2'
        response.put("ACTIVE_ID_1", Integer.valueOf(payload[4] & 0x0F));
        response.put("ACTIVE_ID_2", Integer.valueOf(payload[4] & 0xF0 >> 4));

        // Process 'Properties3'
        response.put("ACTIVE_ID_3", Integer.valueOf(payload[5] & 0x0F));
        response.put("ACTIVE_ID_N", Integer.valueOf(payload[5] & 0xF0 >> 4));

        // Return the map of processed response data;
        return response;
    }

}
