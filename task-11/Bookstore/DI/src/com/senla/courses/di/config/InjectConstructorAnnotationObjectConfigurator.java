package com.senla.courses.di.config;

import com.senla.courses.di.api.annotation.InjectConstructor;
import com.senla.courses.di.api.config.ObjectConfigurator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class InjectConstructorAnnotationObjectConfigurator implements ObjectConfigurator {

    @Override
    public void configure(Object t, ApplicationContext context) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Constructor constructor = t.getClass().getConstructor();
            if (constructor.isAnnotationPresent(InjectConstructor.class)) {
                Class[] parameterTypes = constructor.getParameterTypes();
                List<Object> params = new ArrayList<>();
                for(Class parameter : parameterTypes){
                    params.add(context.getObject(parameter));
                }
                t = constructor.newInstance(params.toArray());
            }
    }
}
