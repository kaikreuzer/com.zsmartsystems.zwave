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
import com.zsmartsystems.zwave.commandclass.impl.CommandClassHrvControlV1;

/**
 * Class to implement loopback tests for command class <b>COMMAND_CLASS_HRV_CONTROL</b> version <b>1</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassHrvControlV1LoopbackTest {
    /**
     * Performs an in/out test of the HRV_CONTROL_MODE_SET command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param mode {@link String}
     */
    public static void testHrvControlModeSetLoopback(String mode) {
        byte[] testPayload = CommandClassHrvControlV1.getHrvControlModeSet(mode);

        Map<String, Object> response = CommandClassHrvControlV1.handleHrvControlModeSet(testPayload);
        assertEquals(mode, (String) response.get("MODE"));
    }

    /**
     * Performs an in/out test of the HRV_CONTROL_MODE_REPORT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param mode {@link String}
     */
    public static void testHrvControlModeReportLoopback(String mode) {
        byte[] testPayload = CommandClassHrvControlV1.getHrvControlModeReport(mode);

        Map<String, Object> response = CommandClassHrvControlV1.handleHrvControlModeReport(testPayload);
        assertEquals(mode, (String) response.get("MODE"));
    }

    /**
     * Performs an in/out test of the HRV_CONTROL_BYPASS_SET command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param bypass {@link Integer}
     */
    public static void testHrvControlBypassSetLoopback(Integer bypass) {
        byte[] testPayload = CommandClassHrvControlV1.getHrvControlBypassSet(bypass);

        Map<String, Object> response = CommandClassHrvControlV1.handleHrvControlBypassSet(testPayload);
        assertEquals(bypass, (Integer) response.get("BYPASS"));
    }

    /**
     * Performs an in/out test of the HRV_CONTROL_BYPASS_REPORT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param bypass {@link Integer}
     */
    public static void testHrvControlBypassReportLoopback(Integer bypass) {
        byte[] testPayload = CommandClassHrvControlV1.getHrvControlBypassReport(bypass);

        Map<String, Object> response = CommandClassHrvControlV1.handleHrvControlBypassReport(testPayload);
        assertEquals(bypass, (Integer) response.get("BYPASS"));
    }

    /**
     * Performs an in/out test of the HRV_CONTROL_VENTILATION_RATE_SET command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param ventilationRate {@link Integer}
     */
    public static void testHrvControlVentilationRateSetLoopback(Integer ventilationRate) {
        byte[] testPayload = CommandClassHrvControlV1.getHrvControlVentilationRateSet(ventilationRate);

        Map<String, Object> response = CommandClassHrvControlV1.handleHrvControlVentilationRateSet(testPayload);
        assertEquals(ventilationRate, (Integer) response.get("VENTILATION_RATE"));
    }

    /**
     * Performs an in/out test of the HRV_CONTROL_VENTILATION_RATE_REPORT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param ventilationRate {@link Integer}
     */
    public static void testHrvControlVentilationRateReportLoopback(Integer ventilationRate) {
        byte[] testPayload = CommandClassHrvControlV1.getHrvControlVentilationRateReport(ventilationRate);

        Map<String, Object> response = CommandClassHrvControlV1.handleHrvControlVentilationRateReport(testPayload);
        assertEquals(ventilationRate, (Integer) response.get("VENTILATION_RATE"));
    }

    /**
     * Performs an in/out test of the HRV_CONTROL_MODE_SUPPORTED_REPORT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param manualControlSupported {@link String}
     * @param bitMask {@link List<String>}
     */
    public static void testHrvControlModeSupportedReportLoopback(String manualControlSupported, List<String> bitMask) {
        byte[] testPayload = CommandClassHrvControlV1.getHrvControlModeSupportedReport(manualControlSupported, bitMask);

        Map<String, Object> response = CommandClassHrvControlV1.handleHrvControlModeSupportedReport(testPayload);
        assertEquals(manualControlSupported, (String) response.get("MANUAL_CONTROL_SUPPORTED"));
        assertEquals(bitMask, (List<String>) response.get("BIT_MASK"));
    }

}
