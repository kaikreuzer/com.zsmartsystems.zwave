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
import com.zsmartsystems.zwave.commandclass.impl.CommandClassMultiCmdV1;

/**
 * Class to implement loopback tests for command class <b>COMMAND_CLASS_MULTI_CMD</b> version <b>1</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassMultiCmdV1LoopbackTest {
    /**
     * Performs an in/out test of the MULTI_CMD_ENCAP command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param numberOfCommands {@link Integer}
     */
    public static void testMultiCmdEncapLoopback(Integer numberOfCommands) {
        byte[] testPayload = CommandClassMultiCmdV1.getMultiCmdEncap(numberOfCommands);

        Map<String, Object> response = CommandClassMultiCmdV1.handleMultiCmdEncap(testPayload);
        assertEquals(numberOfCommands, (Integer) response.get("NUMBER_OF_COMMANDS"));
    }

}
