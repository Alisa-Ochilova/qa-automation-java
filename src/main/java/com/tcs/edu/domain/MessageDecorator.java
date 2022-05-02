package com.tcs.edu.domain;

import com.tcs.edu.domain.Message;

public interface MessageDecorator {
    String decorate(Message message);
}
