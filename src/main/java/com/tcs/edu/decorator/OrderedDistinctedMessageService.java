package com.tcs.edu.decorator;

import com.tcs.edu.domain.*;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;


import static com.tcs.edu.decorator.DoubleCheck.*;
import static com.tcs.edu.domain.LogException.NOT_VALID_ARG_MESSAGE;
import static com.tcs.edu.enums.MessageOrder.*;
import static com.tcs.edu.enums.Doubling.*;

public class OrderedDistinctedMessageService extends ValidatedService implements MessageService {

    private Printer printer;
    private MessageDecorator messageDecorator;

    /**
     * Конструктор, принимающий параметры принтера и декоратора
     */
    public OrderedDistinctedMessageService(Printer printer, MessageDecorator messageDecorator) {
        this.printer = printer;
        this.messageDecorator = messageDecorator;
    }

    /**
     * Проверка входных параметровров на null
     */

    public void process(Message message, Message... messages) throws LogException {
        try {
            super.isArgsValid(message);
            printer.print(messageDecorator.decorate(message));

            for (Message current : messages) {
                printer.print(messageDecorator.decorate(current));
            }
        } catch (IllegalArgumentException e) {
            throw new LogException(NOT_VALID_ARG_MESSAGE, e);
        }
    }

    /**
     * Перегруженный метод, определяющий порядок вывода сообщений для последовательности строковых параметров vararg
     */

    public void process(MessageOrder order, Message message, Message... messages) throws LogException{

        try {
            super.isArgsValid(order, message);
            if (order == DESC) {

                for (int current = messages.length - 1; current >= 0; current--) {
                    printer.print(messageDecorator.decorate(messages[current]));
                }
                printer.print(messageDecorator.decorate(message));

            } else if (order == ASC) process(message, messages);
        } catch (IllegalArgumentException e) {
            throw new LogException(NOT_VALID_ARG_MESSAGE, e);
        }
    }

    /**
     * Перегруженный метод, определяющий характер дублирования значений сообщений последовательности строковых параметров
     */

    public void process(MessageOrder order, Doubling doubling, Message message, Message... messages) throws LogException{

        try {
            super.isArgsValid(order, doubling, message);
            if (doubling == DISTINCT) {
                process(order, message, getArrayWithoutDoubles(messages));
            } else if (doubling == DOUBLES) {
                process(order, message, messages);
            }
        } catch (IllegalArgumentException e) {
            throw new LogException(NOT_VALID_ARG_MESSAGE, e);
        }
    }
}
