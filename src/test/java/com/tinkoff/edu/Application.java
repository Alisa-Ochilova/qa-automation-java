package com.tinkoff.edu;

import com.tcs.edu.decorator.OrderedDistinctedMessageService;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.MessageService;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.repository.HashMapMessageRepository;

import static com.tcs.edu.enums.Severity.MAJOR;
import static com.tcs.edu.enums.Severity.MINOR;

class Application {
    public static void main(String[] args) {
        MessageService service = new OrderedDistinctedMessageService(
                new HashMapMessageRepository(),
                new TimestampMessageDecorator()
        );
        Message message1 = new Message(null, null);
        Message message2 = new Message(MINOR, "Hello world 2!");
        Message message3 = new Message(MAJOR,"Hello world 3!");
        Message message4 = new Message(MAJOR,"Hello world 3!");

        service.process(message1, message2, message3 ,message4);
        service.process(MessageOrder.DESC, message2, message3 ,message4);
        service.process(null, Doubling.DISTINCT, message2, message3 ,message4);
        service.process(MessageOrder.DESC, Doubling.DISTINCT, message2, message3 ,message4);
        System.out.println(new Message(MINOR, "NewMessage"));
        service.process(message3, message2);
        service.findByPrimaryKey(message2.getId());
        service.findAll();
        service.findBySeverity(MAJOR);
    }
}