package com.senla.courses.config;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class FileFormatter extends Formatter {

    private static final MessageFormat messageFormat = new MessageFormat("{3,date,hh:mm:ss / yyyy-mm-dd} - " +
            "{1} - [{2}] - {5} - {4} \n {0} \n");

    @Override
    public String format(LogRecord record) {
        String stacktrace = " ";
        Throwable t = record.getThrown();
        if(t!=null){
            StringWriter sw = new StringWriter();
            t.printStackTrace(new PrintWriter(sw));
            stacktrace = sw.toString();
        }
        Object[] arguments = new Object[6];
        arguments[0] = stacktrace;
        arguments[1] = record.getLevel();
        arguments[2] = record.getSourceClassName();
        arguments[3] = new Date(record.getMillis());
        arguments[4] = record.getMessage();
        arguments[5] = record.getSourceMethodName();
        return messageFormat.format(arguments);
    }
}
