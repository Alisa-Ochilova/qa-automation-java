package com.tcs.edu.printer;



import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.Printer;

import java.util.Arrays;

/**
 * Класс используется для реализации метода вывода данных
 * Реализация метода вывода данных
 * на вход передаются String message
 * побочные эффекты хз какие
 * @author a.ochilova
 */

public class ConsolePrinter implements Printer {
    @Override
    public void print(Message... messages) {
        for (Message current : messages) {
            if (current != null) {
                System.out.println(current.getBody());
            }

        }
    }
}
