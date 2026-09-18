package com.vityarthi.campuslock.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class LoggerUtil {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd HH:mm:ss"
            );

    private LoggerUtil() {
        // Utility class
    }

    public static void log(String message) {

        String timestamp =
                LocalDateTime.now().format(FORMATTER);

        System.out.println(
                "[LOG " + timestamp + "] " + message
        );
    }
}