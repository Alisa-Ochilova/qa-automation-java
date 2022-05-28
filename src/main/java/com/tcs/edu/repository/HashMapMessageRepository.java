package com.tcs.edu.repository;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Severity;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

public class HashMapMessageRepository implements MessageRepository {
    public HashMap<UUID, Message> messages = new HashMap<>();

    public UUID create(Message message) {
        UUID id = UUID.randomUUID();
        messages.put(id, message);
        message.setId(id);
        return id;
    }

    public Message findByPrimaryKey(UUID key){
        return messages.get(key);
    }

    public Collection<Message> findAll() {
        return messages.values();
    }

    public Collection<Message> findBySeverity(Severity by) {
        return messages.values()
                .stream()
                .filter(message -> message.getLevel() == by)
                .collect(Collectors.toList());
    }
}