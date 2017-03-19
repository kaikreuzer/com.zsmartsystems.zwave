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
import com.zsmartsystems.zwave.commandclass.impl.CommandClassUserCodeV1;

/**
 * Class to implement loopback tests for command class <b>COMMAND_CLASS_USER_CODE</b> version <b>1</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassUserCodeV1LoopbackTest {
    /**
     * Performs an in/out test of the USER_CODE_SET command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param userIdentifier {@link Integer}
     * @param userIdStatus {@link String}
     * @param userCode {@link byte[]}
     */
    public static void testUserCodeSetLoopback(Integer userIdentifier, String userIdStatus, byte[] userCode) {
        byte[] testPayload = CommandClassUserCodeV1.getUserCodeSet(userIdentifier, userIdStatus, userCode);

        Map<String, Object> response = CommandClassUserCodeV1.handleUserCodeSet(testPayload);
        assertEquals(userIdentifier, (Integer) response.get("USER_IDENTIFIER"));
        assertEquals(userIdStatus, (String) response.get("USER_ID_STATUS"));
        assertEquals(userCode, (byte[]) response.get("USER_CODE"));
    }

    /**
     * Performs an in/out test of the USER_CODE_GET command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param userIdentifier {@link Integer}
     */
    public static void testUserCodeGetLoopback(Integer userIdentifier) {
        byte[] testPayload = CommandClassUserCodeV1.getUserCodeGet(userIdentifier);

        Map<String, Object> response = CommandClassUserCodeV1.handleUserCodeGet(testPayload);
        assertEquals(userIdentifier, (Integer) response.get("USER_IDENTIFIER"));
    }

    /**
     * Performs an in/out test of the USER_CODE_REPORT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param userIdentifier {@link Integer}
     * @param userIdStatus {@link String}
     * @param userCode {@link byte[]}
     */
    public static void testUserCodeReportLoopback(Integer userIdentifier, String userIdStatus, byte[] userCode) {
        byte[] testPayload = CommandClassUserCodeV1.getUserCodeReport(userIdentifier, userIdStatus, userCode);

        Map<String, Object> response = CommandClassUserCodeV1.handleUserCodeReport(testPayload);
        assertEquals(userIdentifier, (Integer) response.get("USER_IDENTIFIER"));
        assertEquals(userIdStatus, (String) response.get("USER_ID_STATUS"));
        assertEquals(userCode, (byte[]) response.get("USER_CODE"));
    }

    /**
     * Performs an in/out test of the USERS_NUMBER_REPORT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param supportedUsers {@link Integer}
     */
    public static void testUsersNumberReportLoopback(Integer supportedUsers) {
        byte[] testPayload = CommandClassUserCodeV1.getUsersNumberReport(supportedUsers);

        Map<String, Object> response = CommandClassUserCodeV1.handleUsersNumberReport(testPayload);
        assertEquals(supportedUsers, (Integer) response.get("SUPPORTED_USERS"));
    }

}
