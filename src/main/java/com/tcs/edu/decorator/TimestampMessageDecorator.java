package com.tcs.edu.decorator;

import com.tcs.edu.Severity;
import static com.tcs.edu.decorator.SeverityDecorator.mapToString;

import static java.lang.String.format;
import java.time.Instant;

public class TimestampMessageDecorator {

    private static int messageCount;

    private static final int PAGE_SIZE = 2;

    public static String decorator(Severity level, String message){

        if (++messageCount % PAGE_SIZE == 0 ) {
            final String decoratedMessage = format("%d %s %s %s %n---",messageCount,  Instant.now().toString(), message, mapToString(level));
            return decoratedMessage;
        }
        else {
            final String decoratedMessage = format("%d %s %s %s",messageCount,  Instant.now().toString(), message, mapToString(level));
            return decoratedMessage;
        }
    }
}