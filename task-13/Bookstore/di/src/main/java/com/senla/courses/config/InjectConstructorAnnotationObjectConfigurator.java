package com.senla.courses.config;

import com.senla.courses.api.annotation.InjectConstructor;
import com.senla.courses.api.config.ObjectConfigurator;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class InjectConstructorAnnotationObjectConfigurator implements ObjectConfigurator {

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        Class aClass = t.getClass();
            for (Field field : aClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(InjectConstructor.class)) {
                    Constructor constructor = t.getClass().getConstructor();
                    Class[] parameterTypes = constructor.getParameterTypes();
                    List<Object> params = new ArrayList<>();
                    for(Class parameter : parameterTypes){
                        params.add(context.getObject(parameter));
                    }
                    t = constructor.newInstance(params.toArray());
                    field.setAccessible(true);
                    Object object = context.getObject(field.getType());
                    field.set(t, object);
                }
            }




    }
}
