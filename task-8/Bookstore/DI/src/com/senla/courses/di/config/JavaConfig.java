package com.senla.courses.di.config;

import com.senla.courses.di.api.config.Config;
import org.reflections.Reflections;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class JavaConfig implements Config {

    @Override
    public Reflections getScanner() {
        return scanner;
    }

    private final Reflections scanner;

    public JavaConfig(String packageToScan/*, Map<Class, Class> ifc2ImplClass*/) {
        //this.ifc2ImplClass = ifc2ImplClass;
        this.scanner = new Reflections(packageToScan);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public <T> Class getImplClass(Class<T> ifc) {
        //return ifc2ImplClass.computeIfAbsent(ifc, aClass -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
            if (classes.size() != 1) {
                throw new RuntimeException(ifc + " has 0 or more than one impl");
            }

            return classes.iterator().next();
       // });

    }
}
