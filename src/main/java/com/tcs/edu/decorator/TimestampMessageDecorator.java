package com.tcs.edu.decorator;

import static java.lang.String.format;
import java.time.Instant;

public class TimestampMessageDecorator {
    private static final int PAGE_SIZE = 2;
    public static int messageCount;

    public static String decorator(String message) {
        ++messageCount;

        if (messageCount % PAGE_SIZE == 0) {
            return format("%d %s %s %n---", messageCount, Instant.now(), message);
        } else {
            return format("%d %s %s", messageCount, Instant.now(), message);
        }
    }
}