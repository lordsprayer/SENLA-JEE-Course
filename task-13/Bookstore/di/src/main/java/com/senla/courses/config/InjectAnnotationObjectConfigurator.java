package com.senla.courses.config;

import com.senla.courses.api.annotation.Inject;
import com.senla.courses.api.config.ObjectConfigurator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class InjectAnnotationObjectConfigurator implements ObjectConfigurator {

    @Override
    public void configure(Object t, ApplicationContext context) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class[] classes = {t.getClass(), t.getClass().getSuperclass()};
        for(Class aClass: classes){
            for (Field field : aClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Inject.class)) {
                    field.setAccessible(true);
                    Object object = context.getObject(field.getType());
                    field.set(t, object);
                }
            }
        }
    }
}

