package com.tcs.edu.decorator;

import java.util.Objects;

public class DoubleCheck {

    public static boolean hasDuplicate(String message, String... check) {
        for (String checkMessage : check) {
            if (Objects.equals(message, checkMessage)) {
                return true;
            }
        }
        return false;
    }
}
