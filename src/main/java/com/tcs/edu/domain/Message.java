package com.tcs.edu.domain;

import com.tcs.edu.enums.Severity;

public class Message {
    private Severity level;
    private String[] body;

    public Message(Severity level, String... body) {
        this.level = level;
        this.body = body;
    }

    /**
     * На занятии зашили дефолтное значение в конструктора
     */

    public Message(String body) {
        this(Severity.MINOR, body);
    }

    public Severity getLevel() {
        return level;
    }

    public String[] getBody() {
        return body;
    }

    public void setBody(String[] body) {
        this.body = body;
    }
}