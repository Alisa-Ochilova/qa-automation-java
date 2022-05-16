package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;

public abstract class ValidatedService {
    public boolean isArgsValid(MessageOrder order, Doubling doubling, Message message) {
        return message.getBody() != null && message.getLevel() != null && message != null && order != null && doubling != null;
    }
    public boolean isArgsValid(MessageOrder order,  Message message) {
        return message.getBody() != null && message.getLevel() != null && message != null && order != null;
    }
    public boolean isArgsValid(Message message) {
        return message.getBody() != null && message.getLevel() != null && message != null;
    }
}
