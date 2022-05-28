package com.tcs.edu.repository;

import com.tcs.edu.decorator.ValidatedService;
import com.tcs.edu.domain.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Severity;

import java.util.Collection;
import java.util.UUID;

public class MessageServiceCcreate  extends ValidatedService implements MessageRepository{
    private MessageRepository messageRepository = new HashMapMessageRepository();

    public UUID create(Message message) {
        try {
            super.isArgsValid(message);
        } catch (IllegalArgumentException e) {
            throw new LogException(e.getMessage(), e);
        }
        return messageRepository.create(message);
    }

    public Message findByPrimaryKey(UUID key) {
        return messageRepository.findByPrimaryKey(key);
    }

    public Collection<Message> findAll() {
        return messageRepository.findAll();
    }

    public Collection<Message> findBySeverity(Severity by) {
        return messageRepository.findBySeverity(by);
    }
}
