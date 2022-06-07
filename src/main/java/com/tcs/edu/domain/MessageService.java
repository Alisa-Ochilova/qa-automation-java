package com.tcs.edu.domain;

import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.enums.Severity;

import java.util.Collection;
import java.util.UUID;

public interface MessageService {
    void process(MessageOrder order, Doubling doubling, Message message, Message... messages) throws LogException;

    void process(MessageOrder order, Message message, Message... messages) throws LogException;

    void process(Message message, Message... messages) throws LogException;

    Message findByPrimaryKey(UUID key);

    Collection<Message> findAll();

    Collection<Message> findBySeverity(Severity by);
}
