package com.tcs.edu.decorator;

import com.tcs.edu.domain.LogException;
import com.tcs.edu.domain.Message;

public class SeverityDecorator {
    public static String mapToString(Message message) throws LogException {
        String severityString;
        switch (message.getLevel()) {
            case MINOR:
                severityString = " ()";
                break;
            case REGULAR:
                severityString = " (!)";
                break;
            case MAJOR:
                severityString = " (!!!)";
                break;

            default:
                return "Нет такого Severity";
        }
        return severityString;
    }
}
