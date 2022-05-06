package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

import java.util.LinkedHashSet;

import static java.util.Arrays.asList;

public class DoubleCheck {

    public static Message[] getArrayWithoutDoubles(Message[] messages) {
        var s = new LinkedHashSet<>(asList(messages));
        return s.toArray(Message[]::new);
    }
}

