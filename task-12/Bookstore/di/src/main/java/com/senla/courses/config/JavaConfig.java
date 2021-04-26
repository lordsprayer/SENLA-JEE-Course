package com.senla.courses.config;

import com.senla.courses.api.config.Config;
import org.reflections.Reflections;

import java.util.Set;

public class JavaConfig implements Config {

    @Override
    public Reflections getScanner() {
        return scanner;
    }

    private final Reflections scanner;

    public JavaConfig(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public <T> Class getImplClass(Class<T> ifc) {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
            if (classes.size() != 1) {
                throw new RuntimeException(ifc + " has 0 or more than one impl");
            }
            return classes.iterator().next();
    }
}
