package com.senla.courses.config;


import java.lang.reflect.InvocationTargetException;

public class Application {
    public static ApplicationContext run(String packageToScan) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        JavaConfig config = new JavaConfig(packageToScan);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setFactory(objectFactory);
        return context;
    }
}
