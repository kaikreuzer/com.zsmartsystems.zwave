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
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_DEVICE_RESET_LOCALLY</b> version <b>1</b>.
 * <p>
 * Command Class Device Reset Locally
 * <p>
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassDeviceResetLocallyV1 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassDeviceResetLocallyV1.class);

    /**
     * Integer command class key for COMMAND_CLASS_DEVICE_RESET_LOCALLY
     */
    public final static int COMMAND_CLASS_KEY = 0x5A;

    /**
     * Device Reset Locally Notification Command Constant
     */
    public final static int DEVICE_RESET_LOCALLY_NOTIFICATION = 0x01;


    /**
     * Creates a new message with the DEVICE_RESET_LOCALLY_NOTIFICATION command.
     * <p>
     * Device Reset Locally Notification
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getDeviceResetLocallyNotification() {
        logger.debug("Creating command message DEVICE_RESET_LOCALLY_NOTIFICATION version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(DEVICE_RESET_LOCALLY_NOTIFICATION);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the DEVICE_RESET_LOCALLY_NOTIFICATION command.
     * <p>
     * Device Reset Locally Notification
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleDeviceResetLocallyNotification(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }

}
