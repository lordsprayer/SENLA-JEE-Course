package com.senla.courses.di.config;

import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.di.api.config.Config;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("ClassEscapesDefinedScope")
public class ApplicationContext {

    @SuppressWarnings("rawtypes")
    private final Map<Class, Object> cache = new ConcurrentHashMap<>();
    private final Config config;
    private ObjectFactory factory;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public Config getConfig() {
        return config;
    }

    public void setFactory(ObjectFactory factory) {
        this.factory = factory;
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> type) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }

        Class<? extends T> implClass = type;

        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        T t = factory.createObject(implClass);

        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, t);
        }

        return t;
    }
}
