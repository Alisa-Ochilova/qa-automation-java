package com.tcs.edu.domain;

public class LogException extends RuntimeException {
    public static final String NOT_VALID_ARG_MESSAGE = "NOT_VALID_ARG_MESSAGE";

    public LogException(String  message, Throwable cause) {
        super(message, cause);
    }
}

