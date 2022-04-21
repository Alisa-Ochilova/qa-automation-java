package com.tcs.edu.printer;

import com.tcs.edu.enams.Severity;

/**
 * Класс используется для реализации метода вывода данных
 * Реализация метода вывода данных
 * на вход передаются String message
 * побочные эффекты хз какие
 * @author a.ochilova
 */

public class ConsolePrinter {
    public static void print(String message, Severity level) {
        System.out.println(message);
    }
}
