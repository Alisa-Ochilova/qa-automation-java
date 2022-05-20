package com.tcs.edu.domain;

public interface MessageDecorator {
    Message decorate(Message message) throws LogException;
}
