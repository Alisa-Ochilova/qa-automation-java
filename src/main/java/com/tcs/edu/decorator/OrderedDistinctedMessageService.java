package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.MessageDecorator;
import com.tcs.edu.domain.MessageService;
import com.tcs.edu.domain.Printer;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.printer.ConsolePrinter;

import static com.tcs.edu.decorator.DoubleCheck.*;
import static com.tcs.edu.enums.MessageOrder.*;
import static com.tcs.edu.enums.Doubling.*;

public class OrderedDistinctedMessageService implements MessageService {

    /**
     * Проверка входных параметровров на null
     */

    public void process(Message... messages) {
        Printer printer = new ConsolePrinter();
        MessageDecorator messageDecorator = new TimestampMessageDecorator();

        for (Message  current : messages) {
            if (current != null) {
                printer.print(messageDecorator.decorate(current));
            }
        }
    }


    /**
     * Перегруженный метод, определяющий порядок вывода сообщений для последовательности строковых параметров vararg
     */

    public void process(MessageOrder order, Message... messages) {
        if (order == null) return;

        if (order == DESC) {

            Message[] messageRevert = new Message[messages.length];;
            int index = 0;

            for (int current = messages.length - 1; current >= 0; current--) {
                messageRevert[current] = messages[index];
                index++;
            }
            messages = messageRevert;
            process(messages);
        } else if (order == ASC) process(messages);
    }

    /**
     * Перегруженный метод, определяющий характер дублирования значений сообщений последовательности строковых параметров
     */

    public void process(MessageOrder order, Doubling doubling, Message... messages) {
        if (doubling == null) return;
        if (doubling == DISTINCT) {

            messages = getArrayWithoutDoubles(messages);
            process(order, messages);


        } else if (doubling == DOUBLES) {
            process(order, messages);
        }
    }
}
