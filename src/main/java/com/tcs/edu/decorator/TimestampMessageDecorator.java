package com.tcs.edu.decorator;

import java.time.Instant;
import static java.lang.String.format;

public class TimestampMessageDecorator {

    private static int messageCount;

    public static String decorator(String message){

        if (++messageCount % 2 == 0 ) {
            final String decoratedMessage = format("%d %s %s %n---",messageCount,  Instant.now().toString(), message);
            return decoratedMessage;
        }
        else {
            final String decoratedMessage = format("%d %s %s",messageCount,  Instant.now().toString(), message);
            return decoratedMessage;
        }
    }
}