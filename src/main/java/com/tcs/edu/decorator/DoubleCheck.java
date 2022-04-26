package com.tcs.edu.decorator;

import java.util.HashSet;
import static java.util.Arrays.asList;

public class DoubleCheck {

    public static String[] getArrayWithoutDoubles(String[] array) {
        var s = new HashSet<>(asList(array));
        return s.toArray(String[]::new);

    }
}

