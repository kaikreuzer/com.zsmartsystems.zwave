/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zwave.commandclass.impl.loopback;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.List;
import com.zsmartsystems.zwave.commandclass.impl.CommandClassSensorBinaryV2;

/**
 * Class to implement loopback tests for command class <b>COMMAND_CLASS_SENSOR_BINARY</b> version <b>2</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassSensorBinaryV2LoopbackTest {
    /**
     * Performs an in/out test of the SENSOR_BINARY_GET command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param sensorType {@link String}
     */
    public static void testSensorBinaryGetLoopback(String sensorType) {
        byte[] testPayload = CommandClassSensorBinaryV2.getSensorBinaryGet(sensorType);

        Map<String, Object> response = CommandClassSensorBinaryV2.handleSensorBinaryGet(testPayload);
        assertEquals(sensorType, (String) response.get("SENSOR_TYPE"));
    }

    /**
     * Performs an in/out test of the SENSOR_BINARY_REPORT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param sensorValue {@link String}
     * @param sensorType {@link String}
     */
    public static void testSensorBinaryReportLoopback(String sensorValue, String sensorType) {
        byte[] testPayload = CommandClassSensorBinaryV2.getSensorBinaryReport(sensorValue, sensorType);

        Map<String, Object> response = CommandClassSensorBinaryV2.handleSensorBinaryReport(testPayload);
        assertEquals(sensorValue, (String) response.get("SENSOR_VALUE"));
        assertEquals(sensorType, (String) response.get("SENSOR_TYPE"));
    }

    /**
     * Performs an in/out test of the SENSOR_BINARY_SUPPORTED_SENSOR_REPORT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param bitMask {@link List<String>}
     */
    public static void testSensorBinarySupportedSensorReportLoopback(List<String> bitMask) {
        byte[] testPayload = CommandClassSensorBinaryV2.getSensorBinarySupportedSensorReport(bitMask);

        Map<String, Object> response = CommandClassSensorBinaryV2.handleSensorBinarySupportedSensorReport(testPayload);
        assertEquals(bitMask, (List<String>) response.get("BIT_MASK"));
    }

}
