package com.senla.courses.di.config;

import com.senla.courses.di.api.annotation.Inject;
import com.senla.courses.di.api.config.ObjectConfigurator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class InjectAnnotationObjectConfigurator implements ObjectConfigurator {

    @Override
    public void configure(Object t, ApplicationContext context) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                try {
                    field.setAccessible(true);
                } catch (Exception e){
                    e.printStackTrace();
                }
                Object object = context.getObject(field.getType());
                field.set(t, object);
            }
        }
    }
}
