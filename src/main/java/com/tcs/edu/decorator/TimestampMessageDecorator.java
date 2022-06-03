package com.tcs.edu.decorator;

import com.tcs.edu.domain.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.MessageDecorator;

import java.time.LocalDate;

import static com.tcs.edu.decorator.SeverityDecorator.mapToString;
import static java.lang.String.format;

public class TimestampMessageDecorator implements MessageDecorator  {
    private static final int PAGE_SIZE = 2;
    public  int messageCount;

    public Message decorate(Message message) throws LogException {
        ++messageCount;

        if (messageCount % PAGE_SIZE == 0) {
            message.setBody(format("%d %s %s %s %n---", messageCount, LocalDate.now(), message.getBody(), mapToString(message)));
            return message;
        } else {
            message.setBody(format("%d %s %s %s", messageCount, LocalDate.now(), message.getBody(), mapToString(message)));
            return message;
        }
    }
}