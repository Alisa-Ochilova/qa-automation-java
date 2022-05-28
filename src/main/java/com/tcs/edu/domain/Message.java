package com.tcs.edu.domain;

import com.tcs.edu.enums.Severity;

import java.util.Objects;
import java.util.UUID;

public class Message {
    private Severity level;
    private String body;
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Message(Severity level, String body) {
        this.level = level;
        this.body = body;
        this.id = id;
    }

    public Severity getLevel() {
        return level;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "level=" + level +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}