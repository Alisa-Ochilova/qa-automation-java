package com.tcs.edu;

import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.enums.Severity;

import static com.tcs.edu.decorator.SeverityDecorator.mapToString;
import static com.tcs.edu.decorator.TimestampMessageDecorator.decorator;
import static com.tcs.edu.decorator.DoubleCheck.*;
import static com.tcs.edu.printer.ConsolePrinter.print;
import static com.tcs.edu.enums.MessageOrder.*;
import static com.tcs.edu.enums.Doubling.*;

public class MessageService {

    /**
     * Проверка входных параметровров на null
     */

    public static void process(Severity level, String message, String... messages) {
        if (level == null) return;

        if (message != null) {
            print(decorator(message + mapToString(level)), level);
        }
        for (String current : messages) {
            if (current != null) {
                print(decorator(current + mapToString(level)), level);
            }
        }
    }


    /**
     * Перегруженный метод, определяющий порядок вывода сообщений для последовательности строковых параметров vararg
     */

    public static void process(Severity level, MessageOrder order, String message, String... messages) {
        if (order == null) return;

        if (order == DESC) {

            var messageRevert = new String[messages.length + 1];
            messageRevert[messages.length] = message;
            int index = 0;

            for (int current = messages.length - 1; current >= 0; current--) {
                messageRevert[current] = messages[index];
                index++;
            }

            process(level, null, messageRevert);


        } else if (order == ASC) MessageService.process(level, message, messages);
    }

    /**
     * Перегруженный метод, определяющий характер дублирования значений сообщений последовательности строковых параметров
     */

    public static void process(Severity level, MessageOrder order, Doubling doubling, String message, String... messages) {
        if (doubling == null) return;
        if (doubling == DISTINCT) {
            var messageRevert = new String[messages.length + 1];
            messageRevert[0] = message;

            for (int current = 0; current < messages.length; current++) {
                messageRevert[1 + current] = messages[current];

            }
            process(level, order, null, getArrayWithoutDoubles(messageRevert));


        } else if (doubling == DOUBLES) {
            process(level, order, message, messages);
        }
    }
}
