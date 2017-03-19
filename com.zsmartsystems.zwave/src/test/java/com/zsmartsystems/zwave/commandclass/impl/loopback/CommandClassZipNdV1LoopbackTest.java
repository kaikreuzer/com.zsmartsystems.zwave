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
import com.zsmartsystems.zwave.commandclass.impl.CommandClassZipNdV1;

/**
 * Class to implement loopback tests for command class <b>COMMAND_CLASS_ZIP_ND</b> version <b>1</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassZipNdV1LoopbackTest {
    /**
     * Performs an in/out test of the ZIP_NODE_ADVERTISEMENT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param validity {@link String}
     * @param local {@link Boolean}
     * @param nodeId {@link Integer}
     * @param ipv6Address {@link byte[]}
     * @param homeId {@link byte[]}
     */
    public static void testZipNodeAdvertisementLoopback(String validity, Boolean local, Integer nodeId,
            byte[] ipv6Address, byte[] homeId) {
        byte[] testPayload = CommandClassZipNdV1.getZipNodeAdvertisement(validity, local, nodeId, ipv6Address, homeId);

        Map<String, Object> response = CommandClassZipNdV1.handleZipNodeAdvertisement(testPayload);
        assertEquals(validity, (String) response.get("VALIDITY"));
        assertEquals(local, (Boolean) response.get("LOCAL"));
        assertEquals(nodeId, (Integer) response.get("NODE_ID"));
        assertEquals(ipv6Address, (byte[]) response.get("IPV6_ADDRESS"));
        assertEquals(homeId, (byte[]) response.get("HOME_ID"));
    }

    /**
     * Performs an in/out test of the ZIP_NODE_SOLICITATION command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param nodeId {@link Integer}
     * @param ipv6Address {@link byte[]}
     */
    public static void testZipNodeSolicitationLoopback(Integer nodeId, byte[] ipv6Address) {
        byte[] testPayload = CommandClassZipNdV1.getZipNodeSolicitation(nodeId, ipv6Address);

        Map<String, Object> response = CommandClassZipNdV1.handleZipNodeSolicitation(testPayload);
        assertEquals(nodeId, (Integer) response.get("NODE_ID"));
        assertEquals(ipv6Address, (byte[]) response.get("IPV6_ADDRESS"));
    }

    /**
     * Performs an in/out test of the ZIP_INV_NODE_SOLICITATION command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param local {@link Boolean}
     * @param nodeId {@link Integer}
     */
    public static void testZipInvNodeSolicitationLoopback(Boolean local, Integer nodeId) {
        byte[] testPayload = CommandClassZipNdV1.getZipInvNodeSolicitation(local, nodeId);

        Map<String, Object> response = CommandClassZipNdV1.handleZipInvNodeSolicitation(testPayload);
        assertEquals(local, (Boolean) response.get("LOCAL"));
        assertEquals(nodeId, (Integer) response.get("NODE_ID"));
    }

}
