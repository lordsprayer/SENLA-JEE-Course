package com.senla.courses.config;

import com.senla.courses.api.config.ObjectConfigurator;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectFactory {

    private static final Logger log = Logger.getLogger(ObjectFactory.class.getName());
    private final ApplicationContext context;
    private final List<ObjectConfigurator> configurators = new ArrayList<>();


    public ObjectFactory(ApplicationContext context) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        this.context = context;
            for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
                configurators.add(aClass.getDeclaredConstructor().newInstance());
            }
    }

    public <T> T createObject(Class<T> implClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        T t = create(implClass);
        configure(t);
        return t;
    }

    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> {
            try {
                objectConfigurator.configure(t,context);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException | FileNotFoundException e) {
                log.log(Level.WARNING, "Object configuration error");
            }
        });
    }

    private <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        return implClass.getDeclaredConstructor().newInstance();
    }
}
