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
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_BASIC_WINDOW_COVERING</b> version <b>1</b>.<br>
 * <p>
 * Command Class Basic Window Covering<br>
 *
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.<br>
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassBasicWindowCoveringV1 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassBasicWindowCoveringV1.class);

    /**
     * Integer command class key for COMMAND_CLASS_BASIC_WINDOW_COVERING
     */
    public final static int COMMAND_CLASS_KEY = 0x50;

    /**
     * Basic Window Covering Start Level Change Command Constant
     */
    public final static int BASIC_WINDOW_COVERING_START_LEVEL_CHANGE = 0x01;
    /**
     * Basic Window Covering Stop Level Change Command Constant
     */
    public final static int BASIC_WINDOW_COVERING_STOP_LEVEL_CHANGE = 0x02;

    /**
     * Creates a new message with the BASIC_WINDOW_COVERING_START_LEVEL_CHANGE command.<br>
     * <p>
     * Basic Window Covering Start Level Change<br>
     *
     * @param openClose {@link Boolean}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getBasicWindowCoveringStartLevelChange(Boolean openClose) {
        logger.debug("Creating command message BASIC_WINDOW_COVERING_START_LEVEL_CHANGE version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(BASIC_WINDOW_COVERING_START_LEVEL_CHANGE);

        // Process 'Level'
        outputData.write(openClose ? 0x40 : 0);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the BASIC_WINDOW_COVERING_START_LEVEL_CHANGE command<br>
     * <p>
     * Basic Window Covering Start Level Change<br>
     * <p>
     * The output data {@link Map} has the following properties<br>
     *
     * <ul>
     * <li>OPEN_CLOSE {@link Boolean}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleBasicWindowCoveringStartLevelChange(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Level'
        response.put("OPEN_CLOSE", Boolean.valueOf((payload[2] & 0x40) != 0));

        // Return the map of processed response data;
        return response;
    }

    /**
     * Creates a new message with the BASIC_WINDOW_COVERING_STOP_LEVEL_CHANGE command.<br>
     * <p>
     * Basic Window Covering Stop Level Change<br>
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getBasicWindowCoveringStopLevelChange() {
        logger.debug("Creating command message BASIC_WINDOW_COVERING_STOP_LEVEL_CHANGE version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(BASIC_WINDOW_COVERING_STOP_LEVEL_CHANGE);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the BASIC_WINDOW_COVERING_STOP_LEVEL_CHANGE command<br>
     * <p>
     * Basic Window Covering Stop Level Change<br>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleBasicWindowCoveringStopLevelChange(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }

}
