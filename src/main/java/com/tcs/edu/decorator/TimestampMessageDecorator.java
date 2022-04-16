package com.tcs.edu.decorator;

import static java.lang.String.format;
import java.time.Instant;

public class TimestampMessageDecorator {

    public static int messageCount;
      public static String decorator(String message){
            return format("%d %s %s", ++messageCount,  Instant.now().toString(), message);
    }
}