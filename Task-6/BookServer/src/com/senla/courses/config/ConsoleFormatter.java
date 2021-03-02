package com.senla.courses.config;

import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ConsoleFormatter extends Formatter {

    private static final MessageFormat messageFormat = new MessageFormat("{1,date,HH:mm:ss / yyyy-mm-dd} - {0} - {2} \n");

    @Override
    public String format(LogRecord record) {
        Object[] arguments = new Object[3];
        arguments[0] = record.getLevel();
        arguments[1] = new Date(record.getMillis());
        arguments[2] = record.getMessage();
        return messageFormat.format(arguments);
    }
}
