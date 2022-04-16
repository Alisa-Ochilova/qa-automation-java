package com.tcs.edu;

import static com.tcs.edu.decorator.PageSize.pageSizeDecorator;
import static com.tcs.edu.decorator.SeverityDecorator.mapToString;
import static com.tcs.edu.decorator.TimestampMessageDecorator.decorator;
import static com.tcs.edu.printer.ConsolePrinter.print;

public class MessageService {
    public static void process(Severity level,  String... messages){
        for (String current : messages)
         print(decorator(current) + " " + mapToString(level) + pageSizeDecorator());
    }
}
