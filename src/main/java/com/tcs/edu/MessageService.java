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

        if (level != null) {
            if (message != null) {
                print(decorator(message + mapToString(level)), level);
            }
            if (messages != null) {
                for (String current : messages) {
                    print(decorator(current + mapToString(level)), level);
                }
            }
        }
    }


    /**
     * Перегруженный метод, определяющий порядок вывода сообщений для последовательности строковых параметров vararg
     */

    public static void process(Severity level, MessageOrder order, String message, String... messages) {

        if (order == DESC) {
            if (level != null) {
                if (messages != null) {
                    for (int current = messages.length - 1; current >= 0; current--) {
                        print(decorator(messages[current] + mapToString(level)), level);
                    }
                }
                if (message != null) {
                    print(decorator(message + mapToString(level)), level);
                }
            }
        } else if (order == ASC) MessageService.process(level, message, messages);
    }

    /**
     * Перегруженный метод, определяющий характер дублирования значений сообщений последовательности строковых параметров
     */

    public static void process(Severity level, MessageOrder order, Doubling doubling, String message, String... messages) {
        String[] sortingDoublingMessages = new String[messages.length + 1];

        if (doubling == DISTINCT) {
            if (order == DESC) {
                if (level != null) {
                    if (messages != null) {

                        for (int current = messages.length - 1; current >= 0; current--) {
                            if (!hasDuplicate(messages[current], sortingDoublingMessages)) {
                                print(decorator(messages[current] + mapToString(level)), level);
                                sortingDoublingMessages[current] = messages[current];
                            }
                        }
                    }
                    if (message != null) {

                        if (!hasDuplicate(message, sortingDoublingMessages)) {
                            print(decorator(message + mapToString(level)), level);
                        }
                    }
                }
            }

            if (order == ASC) {
                if (level != null) {
                    if (message != null) {
                        print(decorator(message + mapToString(level)), level);
                    }

                    if (messages != null) {

                        sortingDoublingMessages[messages.length] = message;
                        for (int current = 0; current <= messages.length - 1; current++) {
                            if (!hasDuplicate(messages[current], sortingDoublingMessages)) {
                                print(decorator(messages[current] + mapToString(level)), level);
                                sortingDoublingMessages[current] = messages[current];
                            }
                        }
                    }
                }
            } else if (doubling == DOUBLES) {
                MessageService.process(level, order, message, messages);
            }
        }
    }
}