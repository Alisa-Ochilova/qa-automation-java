package com.tinkoff.edu;

import com.tcs.edu.decorator.OrderedDistinctedMessageService;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.MessageService;
import com.tcs.edu.printer.ConsolePrinter;

import static com.tcs.edu.enums.Severity.*;
import static com.tcs.edu.enums.Doubling.*;
import static com.tcs.edu.enums.MessageOrder.*;

class Application {
    public static void main(String[] args) {
        MessageService service = new OrderedDistinctedMessageService(
                new ConsolePrinter(),
                new TimestampMessageDecorator()
        );
        Message message1 = new Message(MINOR, "Hello world 1!");
        Message message2 = new Message(MINOR, "Hello world 2!");
        Message message3 = new Message(MAJOR,"Hello world 3!");
        Message message4 = new Message(MAJOR,"Hello world 3!");
        service.process(DESC, DISTINCT,  message1, message2, message3 ,message4);
    }
}