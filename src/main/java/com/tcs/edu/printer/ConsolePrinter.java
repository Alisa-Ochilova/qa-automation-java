package com.tcs.edu.printer;



import com.tcs.edu.domain.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.Printer;

/**
 * Класс используется для реализации метода вывода данных
 * Реализация метода вывода данных
 * на вход передаются String message
 * побочные эффекты хз какие
 * @author a.ochilova
 */

public class ConsolePrinter implements Printer {
    @Override
    public void print(Message message) throws LogException {
        System.out.println(message.getBody());
    }
}
