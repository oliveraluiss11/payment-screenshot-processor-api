package com.jayway.payment_screenshot_processor_api.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtil {
    public static LocalDateTime getLocalDateTime() {
        return ZonedDateTime.now(ZoneId.of("America/Lima")).toLocalDateTime();
    }
}
