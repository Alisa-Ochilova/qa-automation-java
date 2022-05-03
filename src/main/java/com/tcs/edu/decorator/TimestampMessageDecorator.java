package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.MessageDecorator;
import com.tcs.edu.enums.Severity;

import static com.tcs.edu.decorator.SeverityDecorator.mapToString;
import static java.lang.String.format;

import java.time.Instant;
import java.util.Arrays;

public class TimestampMessageDecorator implements MessageDecorator {
    private static final int PAGE_SIZE = 2;
    public static int messageCount;

    public Message decorate(Message message) {

        String[] newMessageArray = new String[message.getBody().length];

        for (int current = 0; current < message.getBody().length; current++){
            ++messageCount;
            if (messageCount % PAGE_SIZE == 0){
                newMessageArray[current] = format("%d %s %s %s  %n---", messageCount, Instant.now().toString(), message.getBody()[current], mapToString(message));
            }
            else {
                newMessageArray[current] = format("%d %s  %s %s", messageCount, Instant.now().toString(), message.getBody()[current], mapToString(message));
            }
        }
        message.setBody(newMessageArray);
        return message;

    }
}