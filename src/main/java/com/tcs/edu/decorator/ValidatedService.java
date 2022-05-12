package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

public abstract class ValidatedService {
    public boolean isArgsValid(Message message) {
        return message.getBody() != null && message.getLevel() != null && message != null;
    }
}
