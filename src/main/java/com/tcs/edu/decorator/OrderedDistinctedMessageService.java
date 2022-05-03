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

    public void process(Message message) {
        Printer printer = new ConsolePrinter();
        MessageDecorator messageDecorator = new TimestampMessageDecorator();
        if (message.getLevel() == null) return;


                printer.print(messageDecorator.decorate(message));

    }


    /**
     * Перегруженный метод, определяющий порядок вывода сообщений для последовательности строковых параметров vararg
     */

    public void process(MessageOrder order, Message message) {
        if (order == null) return;

        if (order == DESC) {

            var messageRevert = new String[message.getBody().length];
            int index = 0;

            for (int current = message.getBody().length - 1; current >= 0; current--) {
                messageRevert[current] = message.getBody()[index];
                index++;
            }
            message.setBody(messageRevert);
            process(message);
        } else if (order == ASC) process(message);
    }

    /**
     * Перегруженный метод, определяющий характер дублирования значений сообщений последовательности строковых параметров
     */

    public void process(MessageOrder order, Doubling doubling, Message message) {
        if (doubling == null) return;
        if (doubling == DISTINCT) {
            var messageRevert = new String[message.getBody().length];
            int index = 0;

            for (int current = 0; current < message.getBody().length; current++) {
                messageRevert[current] = message.getBody()[index];
                index++;

            }
            message.setBody(getArrayWithoutDoubles(messageRevert));
            process(order, message);


        } else if (doubling == DOUBLES) {
            process(order, message);
        }
    }
}
