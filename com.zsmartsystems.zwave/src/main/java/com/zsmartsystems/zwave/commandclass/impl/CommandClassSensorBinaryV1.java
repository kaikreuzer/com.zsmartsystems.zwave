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
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_SENSOR_BINARY</b> version <b>1</b>.<br>
 * <p>
 * Command Class Sensor Binary<br>
 *
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.<br>
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassSensorBinaryV1 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassSensorBinaryV1.class);

    /**
     * Integer command class key for COMMAND_CLASS_SENSOR_BINARY
     */
    public final static int COMMAND_CLASS_KEY = 0x30;

    /**
     * Sensor Binary Get Command Constant
     */
    public final static int SENSOR_BINARY_GET = 0x02;
    /**
     * Sensor Binary Report Command Constant
     */
    public final static int SENSOR_BINARY_REPORT = 0x03;

    // Constants for Sensor Value
    private static Map<Integer, String> constantSensorValue = new HashMap<Integer, String>();

    static {
        // Constants for Sensor Value
        constantSensorValue.put(0x00, "IDLE");
        constantSensorValue.put(0xFF, "DETECTED_AN_EVENT");
    }

    /**
     * Creates a new message with the SENSOR_BINARY_GET command.<br>
     * <p>
     * Sensor Binary Get<br>
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getSensorBinaryGet() {
        logger.debug("Creating command message SENSOR_BINARY_GET version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(SENSOR_BINARY_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the SENSOR_BINARY_GET command<br>
     * <p>
     * Sensor Binary Get<br>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleSensorBinaryGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the SENSOR_BINARY_REPORT command.<br>
     * <p>
     * Sensor Binary Report<br>
     *
     * @param sensorValue {@link String}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getSensorBinaryReport(String sensorValue) {
        logger.debug("Creating command message SENSOR_BINARY_REPORT version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(SENSOR_BINARY_REPORT);

        // Process 'Sensor Value'
        for (Integer entry : constantSensorValue.keySet()) {
            if (constantSensorValue.get(entry).equals(sensorValue)) {
                outputData.write(entry);
                break;
            }
        }

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the SENSOR_BINARY_REPORT command<br>
     * <p>
     * Sensor Binary Report<br>
     * <p>
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>SENSOR_VALUE {@link String}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleSensorBinaryReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Sensor Value'
        constantSensorValue.get((int) payload[2]);

        // Return the map of processed response data;
        return response;
    }

}
