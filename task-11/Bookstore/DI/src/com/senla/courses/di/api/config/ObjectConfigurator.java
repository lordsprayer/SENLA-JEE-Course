package com.senla.courses.di.api.config;

import com.senla.courses.di.config.ApplicationContext;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public interface ObjectConfigurator {
    void configure(Object t, ApplicationContext context) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, FileNotFoundException;
}
