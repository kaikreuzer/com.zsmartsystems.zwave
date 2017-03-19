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
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_APPLICATION_CAPABILITY</b> version <b>1</b>.
 * <p>
 * Command Class Application Capability
 * <p>
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassApplicationCapabilityV1 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassApplicationCapabilityV1.class);

    /**
     * Integer command class key for COMMAND_CLASS_APPLICATION_CAPABILITY
     */
    public final static int COMMAND_CLASS_KEY = 0x57;

    /**
     * Command Command Class Not Supported Command Constant
     */
    public final static int COMMAND_COMMAND_CLASS_NOT_SUPPORTED = 0x01;


    /**
     * Map holding constants for Properties1
     */
    private static Map<Integer, String> constantProperties1 = new HashMap<Integer, String>();
    static {

        // Constants for Properties1
        constantProperties1.put(0x80, "DYNAMIC");
    }

    /**
     * Creates a new message with the COMMAND_COMMAND_CLASS_NOT_SUPPORTED command.
     * <p>
     * Command Command Class Not Supported
     *
     * @param dynamic {@link Boolean}
     * @param offendingCommandClass {@link Integer}
     * @param offendingCommand {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getCommandCommandClassNotSupported(Boolean dynamic, Integer offendingCommandClass,
            Integer offendingCommand) {
        logger.debug("Creating command message COMMAND_COMMAND_CLASS_NOT_SUPPORTED version 1");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(COMMAND_COMMAND_CLASS_NOT_SUPPORTED);

        // Process 'Properties1'
        outputData.write(dynamic ? 0x80 : 0);

        // Process 'Offending Command Class'
        outputData.write(offendingCommandClass);

        // Process 'Offending Command'
        outputData.write(offendingCommand);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the COMMAND_COMMAND_CLASS_NOT_SUPPORTED command.
     * <p>
     * Command Command Class Not Supported
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>DYNAMIC {@link Boolean}
     * <li>OFFENDING_COMMAND_CLASS {@link Integer}
     * <li>OFFENDING_COMMAND {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleCommandCommandClassNotSupported(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Properties1'
        response.put("DYNAMIC", Boolean.valueOf((payload[2] & 0x80) != 0));

        // Process 'Offending Command Class'
        response.put("OFFENDING_COMMAND_CLASS", Integer.valueOf(payload[3]));

        // Process 'Offending Command'
        response.put("OFFENDING_COMMAND", Integer.valueOf(payload[4]));

        // Return the map of processed response data;
        return response;
    }

}
