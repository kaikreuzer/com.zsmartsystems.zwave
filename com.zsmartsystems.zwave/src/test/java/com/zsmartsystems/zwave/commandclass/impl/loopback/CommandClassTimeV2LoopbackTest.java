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
import com.zsmartsystems.zwave.commandclass.impl.CommandClassTimeV2;

/**
 * Class to implement loopback tests for command class <b>COMMAND_CLASS_TIME</b> version <b>2</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class CommandClassTimeV2LoopbackTest {
    /**
     * Performs an in/out test of the TIME_REPORT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param hourLocalTime {@link Integer}
     * @param rtcFailure {@link Boolean}
     * @param minuteLocalTime {@link Integer}
     * @param secondLocalTime {@link Integer}
     */
    public static void testTimeReportLoopback(Integer hourLocalTime, Boolean rtcFailure, Integer minuteLocalTime,
            Integer secondLocalTime) {
        byte[] testPayload = CommandClassTimeV2.getTimeReport(hourLocalTime, rtcFailure, minuteLocalTime,
                secondLocalTime);

        Map<String, Object> response = CommandClassTimeV2.handleTimeReport(testPayload);
        assertEquals(hourLocalTime, (Integer) response.get("HOUR_LOCAL_TIME"));
        assertEquals(rtcFailure, (Boolean) response.get("RTC_FAILURE"));
        assertEquals(minuteLocalTime, (Integer) response.get("MINUTE_LOCAL_TIME"));
        assertEquals(secondLocalTime, (Integer) response.get("SECOND_LOCAL_TIME"));
    }

    /**
     * Performs an in/out test of the DATE_REPORT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param year {@link Integer}
     * @param month {@link Integer}
     * @param day {@link Integer}
     */
    public static void testDateReportLoopback(Integer year, Integer month, Integer day) {
        byte[] testPayload = CommandClassTimeV2.getDateReport(year, month, day);

        Map<String, Object> response = CommandClassTimeV2.handleDateReport(testPayload);
        assertEquals(year, (Integer) response.get("YEAR"));
        assertEquals(month, (Integer) response.get("MONTH"));
        assertEquals(day, (Integer) response.get("DAY"));
    }

    /**
     * Performs an in/out test of the TIME_OFFSET_SET command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param hourTzo {@link Integer}
     * @param signTzo {@link Boolean}
     * @param minuteTzo {@link Integer}
     * @param minuteOffsetDst {@link Integer}
     * @param signOffsetDst {@link Boolean}
     * @param monthStartDst {@link Integer}
     * @param dayStartDst {@link Integer}
     * @param hourStartDst {@link Integer}
     * @param monthEndDst {@link Integer}
     * @param dayEndDst {@link Integer}
     * @param hourEndDst {@link Integer}
     */
    public static void testTimeOffsetSetLoopback(Integer hourTzo, Boolean signTzo, Integer minuteTzo,
            Integer minuteOffsetDst, Boolean signOffsetDst, Integer monthStartDst, Integer dayStartDst,
            Integer hourStartDst, Integer monthEndDst, Integer dayEndDst, Integer hourEndDst) {
        byte[] testPayload = CommandClassTimeV2.getTimeOffsetSet(hourTzo, signTzo, minuteTzo, minuteOffsetDst,
                signOffsetDst, monthStartDst, dayStartDst, hourStartDst, monthEndDst, dayEndDst, hourEndDst);

        Map<String, Object> response = CommandClassTimeV2.handleTimeOffsetSet(testPayload);
        assertEquals(hourTzo, (Integer) response.get("HOUR_TZO"));
        assertEquals(signTzo, (Boolean) response.get("SIGN_TZO"));
        assertEquals(minuteTzo, (Integer) response.get("MINUTE_TZO"));
        assertEquals(minuteOffsetDst, (Integer) response.get("MINUTE_OFFSET_DST"));
        assertEquals(signOffsetDst, (Boolean) response.get("SIGN_OFFSET_DST"));
        assertEquals(monthStartDst, (Integer) response.get("MONTH_START_DST"));
        assertEquals(dayStartDst, (Integer) response.get("DAY_START_DST"));
        assertEquals(hourStartDst, (Integer) response.get("HOUR_START_DST"));
        assertEquals(monthEndDst, (Integer) response.get("MONTH_END_DST"));
        assertEquals(dayEndDst, (Integer) response.get("DAY_END_DST"));
        assertEquals(hourEndDst, (Integer) response.get("HOUR_END_DST"));
    }

    /**
     * Performs an in/out test of the TIME_OFFSET_REPORT command.
     * <p>
     * Test is designed to ensure that the command generates the same data
     * as the handler processes and is mainly a check of the code generator.
     *
     * @param hourTzo {@link Integer}
     * @param signTzo {@link Boolean}
     * @param minuteTzo {@link Integer}
     * @param minuteOffsetDst {@link Integer}
     * @param signOffsetDst {@link Boolean}
     * @param monthStartDst {@link Integer}
     * @param dayStartDst {@link Integer}
     * @param hourStartDst {@link Integer}
     * @param monthEndDst {@link Integer}
     * @param dayEndDst {@link Integer}
     * @param hourEndDst {@link Integer}
     */
    public static void testTimeOffsetReportLoopback(Integer hourTzo, Boolean signTzo, Integer minuteTzo,
            Integer minuteOffsetDst, Boolean signOffsetDst, Integer monthStartDst, Integer dayStartDst,
            Integer hourStartDst, Integer monthEndDst, Integer dayEndDst, Integer hourEndDst) {
        byte[] testPayload = CommandClassTimeV2.getTimeOffsetReport(hourTzo, signTzo, minuteTzo, minuteOffsetDst,
                signOffsetDst, monthStartDst, dayStartDst, hourStartDst, monthEndDst, dayEndDst, hourEndDst);

        Map<String, Object> response = CommandClassTimeV2.handleTimeOffsetReport(testPayload);
        assertEquals(hourTzo, (Integer) response.get("HOUR_TZO"));
        assertEquals(signTzo, (Boolean) response.get("SIGN_TZO"));
        assertEquals(minuteTzo, (Integer) response.get("MINUTE_TZO"));
        assertEquals(minuteOffsetDst, (Integer) response.get("MINUTE_OFFSET_DST"));
        assertEquals(signOffsetDst, (Boolean) response.get("SIGN_OFFSET_DST"));
        assertEquals(monthStartDst, (Integer) response.get("MONTH_START_DST"));
        assertEquals(dayStartDst, (Integer) response.get("DAY_START_DST"));
        assertEquals(hourStartDst, (Integer) response.get("HOUR_START_DST"));
        assertEquals(monthEndDst, (Integer) response.get("MONTH_END_DST"));
        assertEquals(dayEndDst, (Integer) response.get("DAY_END_DST"));
        assertEquals(hourEndDst, (Integer) response.get("HOUR_END_DST"));
    }

}
