package com.tcs.edu.decorator;

import static com.tcs.edu.decorator.TimestampMessageDecorator.messageCount;

public class PageSize {
    private static final int PAGE_SIZE = 2;

    public static  String pageSizeDecorator(){

        if (messageCount % PAGE_SIZE == 0 ) {
            return "\n---";
        }
        else {
            return "";
        }
    }
}
