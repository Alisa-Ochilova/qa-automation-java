package com.tcs.edu;

import static com.tcs.edu.decorator.TimestampMessageDecorator.decorator;
import static com.tcs.edu.printer.ConsolePrinter.print;

public class MessageService {
    public static void process(Severity level, String... message){

        for(String current : message) print(decorator(level, current));
    }
}
