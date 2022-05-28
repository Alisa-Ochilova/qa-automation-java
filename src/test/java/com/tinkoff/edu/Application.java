package com.tinkoff.edu;

import com.tcs.edu.decorator.OrderedDistinctedMessageService;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.MessageService;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.repository.MessageRepository;
import com.tcs.edu.repository.MessageServiceCcreate;

import static com.tcs.edu.enums.Severity.*;

class Application {
    public static void main(String[] args) {
        MessageService service = new OrderedDistinctedMessageService(
                new ConsolePrinter(),
                new TimestampMessageDecorator()
        );
        MessageRepository repository = new MessageServiceCcreate();
        Message message1 = new Message(null, null);
        Message message2 = new Message(MINOR, "Hello world 2!");
        Message message3 = new Message(MAJOR,"Hello world 3!");
        Message message4 = new Message(MAJOR,"Hello world 3!");

//       service.process(DESC, DISTINCT,  message1, message2, message3 ,message4);
//        service.process(null, DISTINCT, message2, message3 ,message4);
//        service.process(DESC, message2, message3 ,message4);
//        System.out.println(new Message(MINOR, "NewMessage"));

        repository.findByPrimaryKey(message2.getId());
        repository.findAll();
        repository.findBySeverity(MAJOR);
    }
}