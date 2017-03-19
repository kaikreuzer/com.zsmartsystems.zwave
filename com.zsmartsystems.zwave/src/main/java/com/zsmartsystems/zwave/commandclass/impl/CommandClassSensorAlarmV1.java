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
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_SENSOR_ALARM</b> version <b>1</b>.
 * <p>
 * Command Class Sensor Alarm
 * <p>
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassSensorAlarmV1 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassSensorAlarmV1.class);

    /**
     * Integer command class key for COMMAND_CLASS_SENSOR_ALARM
     */
    public final static int COMMAND_CLASS_KEY = 0x9C;

    /**
     * Sensor Alarm Get Command Constant
     */
    public final static int SENSOR_ALARM_GET = 0x01;
    /**
     * Sensor Alarm Report Command Constant
     */
    public final static int SENSOR_ALARM_REPORT = 0x02;
    /**
     * Sensor Alarm Supported Get Command Constant
     */
    public final static int SENSOR_ALARM_SUPPORTED_GET = 0x03;
    /**
     * Sensor Alarm Supported Report Command Constant
     */
    public final static int SENSOR_ALARM_SUPPORTED_REPORT = 0x04;


    /**
     * Map holding constants for Sensor Type
     */
    private static Map<Integer, String> constantSensorType = new HashMap<Integer, String>();

    /**
     * Map holding constants for Sensor State
     */
    private static Map<Integer, String> constantSensorState = new HashMap<Integer, String>();
    static {

        // Constants for Sensor Type
        constantSensorType.put(0x00, "GENERAL_PURPOSE_ALARM");
        constantSensorType.put(0xFF, "RETURN_FIRST_ALARM_ON_SUPPORTED_LIST");
        constantSensorType.put(0x01, "SMOKE_ALARM");
        constantSensorType.put(0x02, "CO_ALARM");
        constantSensorType.put(0x03, "CO2_ALARM");
        constantSensorType.put(0x04, "HEAT_ALARM");
        constantSensorType.put(0x05, "WATER_LEAK_ALARM");

        // Constants for Sensor State
        constantSensorState.put(0x00, "NO_ALARM");
        constantSensorState.put(0xFF, "ALARM");
    }

    /**
     * Creates a new message with the SENSOR_ALARM_GET command.
     * <p>
     * Sensor Alarm Get
     *
     * @param sensorType {@link String}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getSensorAlarmGet(String sensorType) {
        logger.debug("Creating command message SENSOR_ALARM_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(SENSOR_ALARM_GET);

        // Process 'Sensor Type'
        boolean foundSensorType = false;
        for (Integer entry : constantSensorType.keySet()) {
            if (constantSensorType.get(entry).equals(sensorType)) {
                outputData.write(entry);
                foundSensorType = true;
                break;
            }
        }
        if (!foundSensorType) {
            throw new IllegalArgumentException("Unknown constant value '" + sensorType + "' for sensorType");
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the SENSOR_ALARM_GET command.
     * <p>
     * Sensor Alarm Get
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>SENSOR_TYPE {@link String}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleSensorAlarmGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Sensor Type'
        response.put("SENSOR_TYPE", constantSensorType.get(payload[2] & 0xff));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the SENSOR_ALARM_REPORT command.
     * <p>
     * Sensor Alarm Report
     *
     * @param sourceNodeId {@link Integer}
     * @param sensorType {@link String}
     * @param sensorState {@link String}
     * @param seconds {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getSensorAlarmReport(Integer sourceNodeId, String sensorType, String sensorState,
            Integer seconds) {
        logger.debug("Creating command message SENSOR_ALARM_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(SENSOR_ALARM_REPORT);

        // Process 'Source Node ID'
        // Check the node number boundary
        if (sourceNodeId < 1 || sourceNodeId > 232) {
            logger.debug("Node number is out of bounds {}", sourceNodeId);
            return null;
        }
        outputData.write(sourceNodeId);

        // Process 'Sensor Type'
        boolean foundSensorType = false;
        for (Integer entry : constantSensorType.keySet()) {
            if (constantSensorType.get(entry).equals(sensorType)) {
                outputData.write(entry);
                foundSensorType = true;
                break;
            }
        }
        if (!foundSensorType) {
            throw new IllegalArgumentException("Unknown constant value '" + sensorType + "' for sensorType");
        }

        // Process 'Sensor State'
        boolean foundSensorState = false;
        for (Integer entry : constantSensorState.keySet()) {
            if (constantSensorState.get(entry).equals(sensorState)) {
                outputData.write(entry);
                foundSensorState = true;
                break;
            }
        }
        if (!foundSensorState) {
            throw new IllegalArgumentException("Unknown constant value '" + sensorState + "' for sensorState");
        }

        // Process 'Seconds'
        outputData.write((seconds >> 8) & 0xff);
        outputData.write(seconds & 0xff);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the SENSOR_ALARM_REPORT command.
     * <p>
     * Sensor Alarm Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>SOURCE_NODE_ID {@link Integer}
     * <li>SENSOR_TYPE {@link String}
     * <li>SENSOR_STATE {@link String}
     * <li>SECONDS {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleSensorAlarmReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Source Node ID'
        response.put("SOURCE_NODE_ID", Integer.valueOf(payload[2]));

        // Process 'Sensor Type'
        response.put("SENSOR_TYPE", constantSensorType.get(payload[3] & 0xff));

        // Process 'Sensor State'
        response.put("SENSOR_STATE", constantSensorState.get(payload[4] & 0xff));

        // Process 'Seconds'
        response.put("SECONDS", Integer.valueOf(((payload[5] & 0xff) << 8) + (payload[6] & 0xff)));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the SENSOR_ALARM_SUPPORTED_GET command.
     * <p>
     * Sensor Alarm Supported Get
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getSensorAlarmSupportedGet() {
        logger.debug("Creating command message SENSOR_ALARM_SUPPORTED_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(SENSOR_ALARM_SUPPORTED_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the SENSOR_ALARM_SUPPORTED_GET command.
     * <p>
     * Sensor Alarm Supported Get
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleSensorAlarmSupportedGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the SENSOR_ALARM_SUPPORTED_REPORT command.
     * <p>
     * Sensor Alarm Supported Report
     *
     * @param numberOfBitMasks {@link Integer}
     * @param bitMask {@link byte[]}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getSensorAlarmSupportedReport(Integer numberOfBitMasks, byte[] bitMask) {
        logger.debug("Creating command message SENSOR_ALARM_SUPPORTED_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(SENSOR_ALARM_SUPPORTED_REPORT);

        // Process 'Number of Bit Masks'
        outputData.write(numberOfBitMasks);

        // Process 'Bit Mask'
        if (bitMask != null) {
            try {
                outputData.write(bitMask);
            } catch (IOException e) {
            }
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the SENSOR_ALARM_SUPPORTED_REPORT command.
     * <p>
     * Sensor Alarm Supported Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>NUMBER_OF_BIT_MASKS {@link Integer}
     * <li>BIT_MASK {@link byte[]}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleSensorAlarmSupportedReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // We're using variable length fields, so track the offset
        int msgOffset = 2;

        // Process 'Number of Bit Masks'
        response.put("NUMBER_OF_BIT_MASKS", Integer.valueOf(payload[msgOffset]));
        msgOffset += 1;

        // Process 'Bit Mask'
        response.put("BIT_MASK", Integer.valueOf(payload[msgOffset]));
        msgOffset += payload[msgOffset - 1];

        // Return the map of processed response data;
        return response;
    }

}
