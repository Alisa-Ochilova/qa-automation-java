package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;

public abstract class ValidatedService {
    public void isArgsValid(MessageOrder order, Doubling doubling, Message message) {
        isArgsValid(order, message);
        if(doubling == null) throw new IllegalArgumentException("Enter your doubling");
    }
    public void isArgsValid(MessageOrder order,  Message message) {
        isArgsValid(message);
        if(order == null) throw new IllegalArgumentException("Enter your order");
    }
    public void isArgsValid(Message message) {
        if(message.getBody() == null) throw new IllegalArgumentException("Enter your message");
        if(message.getLevel() == null) throw new IllegalArgumentException("Enter your severity");
        if(message == null) throw new IllegalArgumentException("Enter your message and severity");
    }
}
