package com.tcs.edu.domain;

import com.tcs.edu.enums.Severity;

public class Message {
    private Severity level;
    private String body;

    public Message(Severity level, String body) {
        this.level = level;
        this.body = body;
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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Message otherMessage = (Message) obj;
        return body.equals(otherMessage.body) && level == otherMessage.level;
    }

    @Override
    public int hashCode() {
        return body != null ? body.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Message{" +
                "level=" + level +
                ", body='" + body + '\'' +
                '}';
    }
}