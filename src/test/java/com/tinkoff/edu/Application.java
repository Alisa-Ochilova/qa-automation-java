package com.tinkoff.edu;

import static com.tcs.edu.MessageService.*;
import static com.tcs.edu.Severity.*;

class Application {
    public static void main(String[] args) {

        process(MINOR, "Hello world!");
        process(MAJOR, "Hello world!");
        process(REGULAR, "Hello world!");
        process(MINOR, "Hello world!");
        process(MAJOR, "Hello world!");
        process(REGULAR, "Hello world!");
    }
}