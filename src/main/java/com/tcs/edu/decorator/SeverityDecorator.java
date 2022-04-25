package com.tcs.edu.decorator;

import com.tcs.edu.enums.Severity;

public class SeverityDecorator {
    public static String mapToString(Severity severity) {
        String severityString;
        switch (severity) {
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
