package com.tinkoff.edu;

import static com.tcs.edu.decorator.TimestampMessageDecorator.*;
import static com.tcs.edu.printer.ConsolePrinter.print;

class Application {
    public static void main(String[] args) {
        print(decorator("Hello world!"));
        print(decorator("Hello world!"));
        print(decorator("Hello world!"));
        print(decorator("Hello world!"));
        print(decorator("Hello world!"));
        print(decorator("Hello world!"));
    }
}