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
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_VERSION</b> version <b>2</b>.
 * <p>
 * Command Class Version
 * <p>
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.
 * <p>
 * Command class key is 0x86.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassVersionV2 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassVersionV2.class);

    /**
     * Integer command class key for COMMAND_CLASS_VERSION
     */
    public final static int COMMAND_CLASS_KEY = 0x86;

    /**
     * Version Get Command Constant
     */
    public final static int VERSION_GET = 0x11;
    /**
     * Version Report Command Constant
     */
    public final static int VERSION_REPORT = 0x12;
    /**
     * Version Command Class Get Command Constant
     */
    public final static int VERSION_COMMAND_CLASS_GET = 0x13;
    /**
     * Version Command Class Report Command Constant
     */
    public final static int VERSION_COMMAND_CLASS_REPORT = 0x14;


    /**
     * Creates a new message with the VERSION_GET command.
     * <p>
     * Version Get
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getVersionGet() {
        logger.debug("Creating command message VERSION_GET version 2");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(VERSION_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the VERSION_GET command.
     * <p>
     * Version Get
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleVersionGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the VERSION_REPORT command.
     * <p>
     * Version Report
     *
     * @param zWaveLibraryType {@link Integer}
     * @param zWaveProtocolVersion {@link Integer}
     * @param zWaveProtocolSubVersion {@link Integer}
     * @param firmware0Version {@link Integer}
     * @param firmware0SubVersion {@link Integer}
     * @param hardwareVersion {@link Integer}
     * @param numberOfFirmwareTargets {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getVersionReport(Integer zWaveLibraryType, Integer zWaveProtocolVersion,
            Integer zWaveProtocolSubVersion, Integer firmware0Version, Integer firmware0SubVersion,
            Integer hardwareVersion, Integer numberOfFirmwareTargets) {
        logger.debug("Creating command message VERSION_REPORT version 2");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(VERSION_REPORT);

        // Process 'Z-Wave Library Type'
        outputData.write(zWaveLibraryType);

        // Process 'Z-Wave Protocol Version'
        outputData.write(zWaveProtocolVersion);

        // Process 'Z-Wave Protocol Sub Version'
        outputData.write(zWaveProtocolSubVersion);

        // Process 'Firmware 0 Version'
        outputData.write(firmware0Version);

        // Process 'Firmware 0 Sub Version'
        outputData.write(firmware0SubVersion);

        // Process 'Hardware Version'
        outputData.write(hardwareVersion);

        // Process 'Number of firmware targets'
        outputData.write(numberOfFirmwareTargets);

        // Process 'vg'

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the VERSION_REPORT command.
     * <p>
     * Version Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>Z_WAVE_LIBRARY_TYPE {@link Integer}
     * <li>Z_WAVE_PROTOCOL_VERSION {@link Integer}
     * <li>Z_WAVE_PROTOCOL_SUB_VERSION {@link Integer}
     * <li>FIRMWARE_0_VERSION {@link Integer}
     * <li>FIRMWARE_0_SUB_VERSION {@link Integer}
     * <li>HARDWARE_VERSION {@link Integer}
     * <li>NUMBER_OF_FIRMWARE_TARGETS {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleVersionReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Z-Wave Library Type'
        response.put("Z_WAVE_LIBRARY_TYPE", Integer.valueOf(payload[2]));

        // Process 'Z-Wave Protocol Version'
        response.put("Z_WAVE_PROTOCOL_VERSION", Integer.valueOf(payload[3]));

        // Process 'Z-Wave Protocol Sub Version'
        response.put("Z_WAVE_PROTOCOL_SUB_VERSION", Integer.valueOf(payload[4]));

        // Process 'Firmware 0 Version'
        response.put("FIRMWARE_0_VERSION", Integer.valueOf(payload[5]));

        // Process 'Firmware 0 Sub Version'
        response.put("FIRMWARE_0_SUB_VERSION", Integer.valueOf(payload[6]));

        // Process 'Hardware Version'
        response.put("HARDWARE_VERSION", Integer.valueOf(payload[7]));

        // Process 'Number of firmware targets'
        response.put("NUMBER_OF_FIRMWARE_TARGETS", Integer.valueOf(payload[8]));

        // Process 'vg'

        // Create a list to hold the group vg
        List<Map<String, Object>> variantList = new ArrayList<Map<String, Object>>();

        int offset = 9;
        while (offset < payload.length) {
            // Create a map to hold the members of this variant
            Map<String, Object> variant = new HashMap<String, Object>();

            // Process 'Firmware Version'
            variant.put("FIRMWARE_VERSION", Integer.valueOf(payload[9]));

            // Process 'Firmware Sub Version'
            variant.put("FIRMWARE_SUB_VERSION", Integer.valueOf(payload[10]));

            // Add to the list
            variantList.add(variant);

            // Add the length of this variant
            offset += (payload[offset + 6] & 0xFF) >> 0;
        }

        // Add the variant list to the response
        response.put("VG", variantList);

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the VERSION_COMMAND_CLASS_GET command.
     * <p>
     * Version Command Class Get
     *
     * @param requestedCommandClass {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getVersionCommandClassGet(Integer requestedCommandClass) {
        logger.debug("Creating command message VERSION_COMMAND_CLASS_GET version 2");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(VERSION_COMMAND_CLASS_GET);

        // Process 'Requested Command Class'
        outputData.write(requestedCommandClass);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the VERSION_COMMAND_CLASS_GET command.
     * <p>
     * Version Command Class Get
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>REQUESTED_COMMAND_CLASS {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleVersionCommandClassGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Requested Command Class'
        response.put("REQUESTED_COMMAND_CLASS", Integer.valueOf(payload[2]));

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the VERSION_COMMAND_CLASS_REPORT command.
     * <p>
     * Version Command Class Report
     *
     * @param requestedCommandClass {@link Integer}
     * @param commandClassVersion {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getVersionCommandClassReport(Integer requestedCommandClass, Integer commandClassVersion) {
        logger.debug("Creating command message VERSION_COMMAND_CLASS_REPORT version 2");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(VERSION_COMMAND_CLASS_REPORT);

        // Process 'Requested Command Class'
        outputData.write(requestedCommandClass);

        // Process 'Command Class Version'
        outputData.write(commandClassVersion);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the VERSION_COMMAND_CLASS_REPORT command.
     * <p>
     * Version Command Class Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>REQUESTED_COMMAND_CLASS {@link Integer}
     * <li>COMMAND_CLASS_VERSION {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleVersionCommandClassReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Requested Command Class'
        response.put("REQUESTED_COMMAND_CLASS", Integer.valueOf(payload[2]));

        // Process 'Command Class Version'
        response.put("COMMAND_CLASS_VERSION", Integer.valueOf(payload[3]));

        // Return the map of processed response data;
        return response;
    }

}
