package com.tinkoff.edu;

import static com.tcs.edu.MessageService.*;
import static com.tcs.edu.enams.Severity.*;
import static com.tcs.edu.enams.Doubling.*;
import static com.tcs.edu.enams.MessageOrder.*;

class Application {
    public static void main(String[] args) {
        process(MINOR, "Hello world!", "Hello world!");
        process(REGULAR, "Hello world!");
        process(MAJOR, "Hello world!", "Hello world!","Hello world!");
        process(MINOR, DESC, DOUBLES, "Hello world 1!","fgdfgfdgdf", "fgdfgfdgdf");
        process(REGULAR, "Hello world!");
        process(MAJOR, "Hello world!");
        process(null, null,null);
       process(null, "Hello world!",null);
    }
}