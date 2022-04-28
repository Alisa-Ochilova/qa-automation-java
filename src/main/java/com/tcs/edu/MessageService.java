package com.tcs.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;

import static com.tcs.edu.decorator.TimestampMessageDecorator.decorator;
import static com.tcs.edu.decorator.DoubleCheck.*;
import static com.tcs.edu.printer.ConsolePrinter.print;
import static com.tcs.edu.enums.MessageOrder.*;
import static com.tcs.edu.enums.Doubling.*;

public class MessageService {

    /**
     * Проверка входных параметровров на null
     */

    public static void process(Message message) {
        if (message.getLevel() == null) return;

        for (String current : message.getBody()) {
            if (current != null) {
                print(decorator(current , message.getLevel()));
            }
        }
    }


    /**
     * Перегруженный метод, определяющий порядок вывода сообщений для последовательности строковых параметров vararg
     */

    public static void process(MessageOrder order, Message message) {
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

    public static void process(MessageOrder order, Doubling doubling, Message message) {
        if (doubling == null) return;
        if (doubling == DISTINCT) {
            var messageRevert = new String[message.getBody().length];
            int index = 0;

            for (int current = 0; current < message.getBody().length; current++) {
                messageRevert[current] = message.getBody()[index];
                index++;

            }
            message.setBody(getArrayWithoutDoubles(messageRevert));
            process(message);


        } else if (doubling == DOUBLES) {
            process(message);
        }
    }
}
