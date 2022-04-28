package com.tinkoff.edu;

import com.tcs.edu.domain.Message;

import static com.tcs.edu.MessageService.*;
import static com.tcs.edu.enums.Severity.*;
import static com.tcs.edu.enums.Doubling.*;
import static com.tcs.edu.enums.MessageOrder.*;

class Application {
    public static void main(String[] args) {
        Message message1 = new Message(MINOR, "Hello world!", "Hello world!");
        process(message1);
        Message message2 = new Message(MINOR, "Hello world 1!", "Hello world 2!");
        process(DESC, message2);
        Message message3 = new Message(MINOR, "Hello world 1!", "Hello world 4!", "Hello world 4!");
        process(DESC, DISTINCT, message3);
//        process(REGULAR, "Hello world!");
//        process(MAJOR, "Hello world!", "Hello world!", "Hello world!");
//        process(MINOR, DESC, DOUBLES, "Hello world 1!", "fgdfgfdgdf", "fgdfgfdgdf");
//        process(REGULAR, "Hello world!");
//        process(MAJOR, "Hello world!");
//        process(null, null, null);
    }
}