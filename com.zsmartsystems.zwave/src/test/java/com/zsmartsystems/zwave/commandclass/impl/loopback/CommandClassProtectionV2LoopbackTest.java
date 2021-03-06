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
import com.zsmartsystems.zwave.commandclass.impl.CommandClassProtectionV2;

/**
 * Class to implement loopback tests for command class <b>COMMAND_CLASS_PROTECTION</b> version <b>2</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassProtectionV2LoopbackTest {
    /**
     * Performs an in/out test of the PROTECTION_SET command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param localProtectionState {@link Integer}
     * @param rfProtectionState {@link Integer}
     */
    public static void testProtectionSetLoopback(Integer localProtectionState, Integer rfProtectionState) {
        byte[] testPayload = CommandClassProtectionV2.getProtectionSet(localProtectionState, rfProtectionState);

        Map<String, Object> response = CommandClassProtectionV2.handleProtectionSet(testPayload);
        assertEquals(localProtectionState, (Integer) response.get("LOCAL_PROTECTION_STATE"));
        assertEquals(rfProtectionState, (Integer) response.get("RF_PROTECTION_STATE"));
    }

    /**
     * Performs an in/out test of the PROTECTION_REPORT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param localProtectionState {@link Integer}
     * @param rfProtectionState {@link Integer}
     */
    public static void testProtectionReportLoopback(Integer localProtectionState, Integer rfProtectionState) {
        byte[] testPayload = CommandClassProtectionV2.getProtectionReport(localProtectionState, rfProtectionState);

        Map<String, Object> response = CommandClassProtectionV2.handleProtectionReport(testPayload);
        assertEquals(localProtectionState, (Integer) response.get("LOCAL_PROTECTION_STATE"));
        assertEquals(rfProtectionState, (Integer) response.get("RF_PROTECTION_STATE"));
    }

    /**
     * Performs an in/out test of the PROTECTION_SUPPORTED_REPORT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param timeout {@link Boolean}
     * @param exclusiveControl {@link Boolean}
     * @param localProtectionState {@link Integer}
     * @param rfProtectionState {@link Integer}
     */
    public static void testProtectionSupportedReportLoopback(Boolean timeout, Boolean exclusiveControl,
            Integer localProtectionState, Integer rfProtectionState) {
        byte[] testPayload = CommandClassProtectionV2.getProtectionSupportedReport(timeout, exclusiveControl,
                localProtectionState, rfProtectionState);

        Map<String, Object> response = CommandClassProtectionV2.handleProtectionSupportedReport(testPayload);
        assertEquals(timeout, (Boolean) response.get("TIMEOUT"));
        assertEquals(exclusiveControl, (Boolean) response.get("EXCLUSIVE_CONTROL"));
        assertEquals(localProtectionState, (Integer) response.get("LOCAL_PROTECTION_STATE"));
        assertEquals(rfProtectionState, (Integer) response.get("RF_PROTECTION_STATE"));
    }

    /**
     * Performs an in/out test of the PROTECTION_EC_SET command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param nodeId {@link Integer}
     */
    public static void testProtectionEcSetLoopback(Integer nodeId) {
        byte[] testPayload = CommandClassProtectionV2.getProtectionEcSet(nodeId);

        Map<String, Object> response = CommandClassProtectionV2.handleProtectionEcSet(testPayload);
        assertEquals(nodeId, (Integer) response.get("NODE_ID"));
    }

    /**
     * Performs an in/out test of the PROTECTION_EC_REPORT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param nodeId {@link Integer}
     */
    public static void testProtectionEcReportLoopback(Integer nodeId) {
        byte[] testPayload = CommandClassProtectionV2.getProtectionEcReport(nodeId);

        Map<String, Object> response = CommandClassProtectionV2.handleProtectionEcReport(testPayload);
        assertEquals(nodeId, (Integer) response.get("NODE_ID"));
    }

    /**
     * Performs an in/out test of the PROTECTION_TIMEOUT_SET command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param timeout {@link String}
     */
    public static void testProtectionTimeoutSetLoopback(String timeout) {
        byte[] testPayload = CommandClassProtectionV2.getProtectionTimeoutSet(timeout);

        Map<String, Object> response = CommandClassProtectionV2.handleProtectionTimeoutSet(testPayload);
        assertEquals(timeout, (String) response.get("TIMEOUT"));
    }

    /**
     * Performs an in/out test of the PROTECTION_TIMEOUT_REPORT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param timeout {@link String}
     */
    public static void testProtectionTimeoutReportLoopback(String timeout) {
        byte[] testPayload = CommandClassProtectionV2.getProtectionTimeoutReport(timeout);

        Map<String, Object> response = CommandClassProtectionV2.handleProtectionTimeoutReport(testPayload);
        assertEquals(timeout, (String) response.get("TIMEOUT"));
    }

}
