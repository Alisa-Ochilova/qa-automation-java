package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;

public abstract class ValidatedService {
    public void isArgsValid(MessageOrder order, Doubling doubling, Message message) {
        isArgsValid(order, message);
        if(doubling == null) throw new IllegalArgumentException("Doubling cannot be null");
    }
    public void isArgsValid(MessageOrder order,  Message message) {
        isArgsValid(message);
        if(order == null) throw new IllegalArgumentException("Order cannot be null");
    }
    public void isArgsValid(Message message) {
        if(message.getBody() == null) throw new IllegalArgumentException("Message body cannot be null");
        if(message.getLevel() == null) throw new IllegalArgumentException("Severity cannot be null");
        if(message == null) throw new IllegalArgumentException("Message and severity cannot be null");
    }
}
