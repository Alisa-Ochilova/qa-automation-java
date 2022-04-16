package com.tcs.edu.decorator;

import com.tcs.edu.Severity;

public class SeverityDecorator {
    public static String mapToString(Severity severity){
        String severityString = null;
        switch (severity){
            case MINOR: severityString = "()"; break;
            case REGULAR: severityString = "(!)"; break;
            case MAJOR: severityString = "(!!!)"; break;
        }
        return severityString;
    }
}
