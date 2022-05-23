package com.tcs.edu.domain;

public class LogException extends RuntimeException {

    public LogException(String  message, Throwable cause) {
        super(message, cause);
    }
}

