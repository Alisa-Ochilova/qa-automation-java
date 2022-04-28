package com.tcs.edu.decorator;

import com.tcs.edu.enums.Severity;

import static com.tcs.edu.decorator.SeverityDecorator.mapToString;
import static java.lang.String.format;

import java.time.Instant;

public class TimestampMessageDecorator {
    private static final int PAGE_SIZE = 2;
    public static int messageCount;

    public static String decorator(String message, Severity level) {
        ++messageCount;

        if (messageCount % PAGE_SIZE == 0) {
            return format("%d %s %s %s  %n---", messageCount, Instant.now().toString(), message, mapToString(level));
        } else {
            return format("%d %s  %s %s", messageCount, Instant.now().toString(), message, mapToString(level));
        }
    }
}