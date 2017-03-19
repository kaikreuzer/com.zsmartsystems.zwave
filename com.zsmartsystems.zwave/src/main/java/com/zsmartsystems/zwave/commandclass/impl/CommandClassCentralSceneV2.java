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
 * Class to implement the Z-Wave command class <b>COMMAND_CLASS_CENTRAL_SCENE</b> version <b>2</b>.
 * <p>
 * Command Class Central Scene
 * <p>
 * This class provides static methods for processing received messages (message handler) and
 * methods to get a message to send on the Z-Wave network.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassCentralSceneV2 {
    private static final Logger logger = LoggerFactory.getLogger(CommandClassCentralSceneV2.class);

    /**
     * Integer command class key for COMMAND_CLASS_CENTRAL_SCENE
     */
    public final static int COMMAND_CLASS_KEY = 0x5B;

    /**
     * Central Scene Supported Get Command Constant
     */
    public final static int CENTRAL_SCENE_SUPPORTED_GET = 0x01;
    /**
     * Central Scene Supported Report Command Constant
     */
    public final static int CENTRAL_SCENE_SUPPORTED_REPORT = 0x02;
    /**
     * Central Scene Notification Command Constant
     */
    public final static int CENTRAL_SCENE_NOTIFICATION = 0x03;


    /**
     * Map holding constants for Properties1
     */
    private static Map<Integer, String> constantProperties1 = new HashMap<Integer, String>();

    /**
     * Map holding constants for Supported Key Attributes for Scene
     */
    private static Map<Integer, String> constantSupportedKeyAttributesForScene = new HashMap<Integer, String>();
    static {

        // Constants for Properties1
        constantProperties1.put(0x01, "IDENTICAL");

        // Constants for Supported Key Attributes for Scene
        constantSupportedKeyAttributesForScene.put(0x00, "KEY_PRESSED_1_TIME");
        constantSupportedKeyAttributesForScene.put(0x01, "KEY_RELEASED");
        constantSupportedKeyAttributesForScene.put(0x02, "KEY_HELD_DOWN");
        constantSupportedKeyAttributesForScene.put(0x03, "KEY_PRESSED_2_TIMES");
        constantSupportedKeyAttributesForScene.put(0x04, "KEY_PRESSED_3_TIMES");
        constantSupportedKeyAttributesForScene.put(0x05, "KEY_PRESSED_4_TIMES");
        constantSupportedKeyAttributesForScene.put(0x06, "KEY_PRESSED_5_TIMES");
    }

    /**
     * Creates a new message with the CENTRAL_SCENE_SUPPORTED_GET command.
     * <p>
     * Central Scene Supported Get
     *
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getCentralSceneSupportedGet() {
        logger.debug("Creating command message CENTRAL_SCENE_SUPPORTED_GET version 2");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(CENTRAL_SCENE_SUPPORTED_GET);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the CENTRAL_SCENE_SUPPORTED_GET command.
     * <p>
     * Central Scene Supported Get
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleCentralSceneSupportedGet(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the CENTRAL_SCENE_SUPPORTED_REPORT command.
     * <p>
     * Central Scene Supported Report
     *
     * @param supportedScenes {@link Integer}
     * @param identical {@link Boolean}
     * @param numberOfBitMaskBytes {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getCentralSceneSupportedReport(Integer supportedScenes, Boolean identical,
            Integer numberOfBitMaskBytes) {
        logger.debug("Creating command message CENTRAL_SCENE_SUPPORTED_REPORT version 2");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(CENTRAL_SCENE_SUPPORTED_REPORT);

        // Process 'Supported Scenes'
        outputData.write(supportedScenes);

        // Process 'Properties1'
        int valProperties1 = 0;
        valProperties1 |= identical ? 0x01 : 0;
        valProperties1 |= ((numberOfBitMaskBytes << 1) & 0x06);
        outputData.write(valProperties1);

        // Process 'vg1'

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the CENTRAL_SCENE_SUPPORTED_REPORT command.
     * <p>
     * Central Scene Supported Report
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>SUPPORTED_SCENES {@link Integer}
     * <li>IDENTICAL {@link Boolean}
     * <li>NUMBER_OF_BIT_MASK_BYTES {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleCentralSceneSupportedReport(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Supported Scenes'
        response.put("SUPPORTED_SCENES", Integer.valueOf(payload[2]));

        // Process 'Properties1'
        response.put("IDENTICAL", Boolean.valueOf((payload[3] & 0x01) != 0));
        response.put("NUMBER_OF_BIT_MASK_BYTES", Integer.valueOf(payload[3] & 0x06 >> 1));

        // Process 'vg1'

        // Create a list to hold the group vg1
        List<Map<String, Object>> variantList = new ArrayList<Map<String, Object>>();

        int offset = 4;
        while (offset < payload.length) {
            // Create a map to hold the members of this variant
            Map<String, Object> variant = new HashMap<String, Object>();

            // Process 'Supported Key Attributes for Scene'
            List<String> responseSupportedKeyAttributesForScene = new ArrayList<String>();
            int lenSupportedKeyAttributesForScene = (payload[5] & 0x06 >> 1) * 8;
            for (int cntSupportedKeyAttributesForScene = 0; cntSupportedKeyAttributesForScene < lenSupportedKeyAttributesForScene; cntSupportedKeyAttributesForScene++) {
                if ((payload[4 + (cntSupportedKeyAttributesForScene / 8)] & (1 << cntSupportedKeyAttributesForScene % 8)) == 0) {
                    continue;
                }
                responseSupportedKeyAttributesForScene.add(constantSupportedKeyAttributesForScene.get(cntSupportedKeyAttributesForScene));
            }
            variant.put("SUPPORTED_KEY_ATTRIBUTES_FOR_SCENE", responseSupportedKeyAttributesForScene);

            // Add to the list
            variantList.add(variant);

            // Add the length of this variant
            offset += (payload[offset + 0] & 0xFF) >> 0;
        }

        // Add the variant list to the response
        response.put("VG1", variantList);

        // Return the map of processed response data;
        return response;
    }


    /**
     * Creates a new message with the CENTRAL_SCENE_NOTIFICATION command.
     * <p>
     * Central Scene Notification
     *
     * @param sequenceNumber {@link Integer}
     * @param keyAttributes {@link String}
     * @param sceneNumber {@link Integer}
     * @return the {@link byte[]} array with the command to send
     */
    static public byte[] getCentralSceneNotification(Integer sequenceNumber, String keyAttributes, Integer sceneNumber) {
        logger.debug("Creating command message CENTRAL_SCENE_NOTIFICATION version 2");

        ByteArrayOutputStream outputData = new ByteArrayOutputStream();
        outputData.write(COMMAND_CLASS_KEY);
        outputData.write(CENTRAL_SCENE_NOTIFICATION);

        // Process 'Sequence Number'
        outputData.write(sequenceNumber);

        // Process 'Properties1'
        int valkeyAttributes;
        switch (keyAttributes) {
            case "KEY_PRESSED_1_TIME":
                valkeyAttributes = 0;
                break;
            case "KEY_RELEASED":
                valkeyAttributes = 1;
                break;
            case "KEY_HELD_DOWN":
                valkeyAttributes = 2;
                break;
            case "KEY_PRESSED_2_TIMES":
                valkeyAttributes = 3;
                break;
            case "KEY_PRESSED_3_TIMES":
                valkeyAttributes = 4;
                break;
            case "KEY_PRESSED_4_TIMES":
                valkeyAttributes = 5;
                break;
            case "KEY_PRESSED_5_TIMES":
                valkeyAttributes = 6;
                break;
            default:
                throw new IllegalArgumentException("Unknown enum value for keyAttributes: " + keyAttributes);
        }
        outputData.write(valkeyAttributes & 0x07);

        // Process 'Scene Number'
        outputData.write(sceneNumber);

        return outputData.toByteArray();
    }

    /**
     * Processes a received frame with the CENTRAL_SCENE_NOTIFICATION command.
     * <p>
     * Central Scene Notification
     * <p>
     * The output data {@link Map} has the following properties -:
     *
     * <ul>
     * <li>SEQUENCE_NUMBER {@link Integer}
     * <li>KEY_ATTRIBUTES {@link String}
     * <li>SCENE_NUMBER {@link Integer}
     * </ul>
     *
     * @param payload the {@link byte[]} payload data to process
     * @return a {@link Map} of processed response data
     */
    public static Map<String, Object> handleCentralSceneNotification(byte[] payload) {
        // Create our response map
        Map<String, Object> response = new HashMap<String, Object>();

        // Process 'Sequence Number'
        response.put("SEQUENCE_NUMBER", Integer.valueOf(payload[2]));

        // Process 'Properties1'
        switch (payload[3] & 0x07) {
            case 0x00:
                response.put("KEY_ATTRIBUTES", "KEY_PRESSED_1_TIME");
                break;
            case 0x01:
                response.put("KEY_ATTRIBUTES", "KEY_RELEASED");
                break;
            case 0x02:
                response.put("KEY_ATTRIBUTES", "KEY_HELD_DOWN");
                break;
            case 0x03:
                response.put("KEY_ATTRIBUTES", "KEY_PRESSED_2_TIMES");
                break;
            case 0x04:
                response.put("KEY_ATTRIBUTES", "KEY_PRESSED_3_TIMES");
                break;
            case 0x05:
                response.put("KEY_ATTRIBUTES", "KEY_PRESSED_4_TIMES");
                break;
            case 0x06:
                response.put("KEY_ATTRIBUTES", "KEY_PRESSED_5_TIMES");
                break;
            default:
                logger.debug("Unknown enum value {} for KEY_ATTRIBUTES", String.format("0x%02X", 3));
        }

        // Process 'Scene Number'
        response.put("SCENE_NUMBER", Integer.valueOf(payload[4]));

        // Return the map of processed response data;
        return response;
    }

}
