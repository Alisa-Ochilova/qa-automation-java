package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.MessageDecorator;

import static com.tcs.edu.decorator.SeverityDecorator.mapToString;
import static java.lang.String.format;

import java.time.Instant;

public class TimestampMessageDecorator implements MessageDecorator {
    private static final int PAGE_SIZE = 2;
    public static int messageCount;

    public Message decorate(Message message) {
        ++messageCount;

        if (messageCount % PAGE_SIZE == 0) {
            message.setBody(format("%d %s %s %s  %n---", messageCount, Instant.now().toString(), message.getBody(), mapToString(message)));
            return message;
        } else {
            message.setBody(format("%d %s  %s %s", messageCount, Instant.now().toString(), message.getBody(), mapToString(message)));
            return message;
        }
    }
}