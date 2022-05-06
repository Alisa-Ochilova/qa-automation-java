package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.MessageDecorator;
import com.tcs.edu.domain.MessageService;
import com.tcs.edu.domain.Printer;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;


import static com.tcs.edu.decorator.DoubleCheck.*;
import static com.tcs.edu.enums.MessageOrder.*;
import static com.tcs.edu.enums.Doubling.*;

public class OrderedDistinctedMessageService implements MessageService {

    private Printer printer;
    private MessageDecorator messageDecorator;

    /**
     *
     * Конструктор, принимающий параметры принтера и декоратора
     *
     */
    public OrderedDistinctedMessageService(Printer printer, MessageDecorator messageDecorator) {
        this.printer = printer;
        this.messageDecorator = messageDecorator;
    }

    /**
     * Проверка входных параметровров на null
     */

    public void process(Message message, Message... messages) {

        if (message.getLevel() == null) return;

        if (message.getBody() != null) {
            printer.print(messageDecorator.decorate(message));

            for (Message current : messages) {
                if (current != null) {
                    printer.print(messageDecorator.decorate(current));
                }
            }
        }
    }

    /**
     * Перегруженный метод, определяющий порядок вывода сообщений для последовательности строковых параметров vararg
     */

    public void process(MessageOrder order, Message message, Message... messages) {
        if (order == null) return;

        if (order == DESC) {

            for (int current = messages.length - 1; current >= 0; current--) {
                printer.print(messageDecorator.decorate(messages[current]));
            }
            printer.print(messageDecorator.decorate(message));

        } else if (order == ASC) process(message, messages);
    }

    /**
     * Перегруженный метод, определяющий характер дублирования значений сообщений последовательности строковых параметров
     */

    public void process(MessageOrder order, Doubling doubling, Message message, Message... messages) {

        if (doubling == null) return;
        if (doubling == DISTINCT) {
            process(order, message, getArrayWithoutDoubles(messages));
            } else if (doubling == DOUBLES) {
                process(order, message, messages);
            }
    }
}
