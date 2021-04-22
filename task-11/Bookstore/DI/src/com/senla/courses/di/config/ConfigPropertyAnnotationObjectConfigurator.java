package com.senla.courses.di.config;

import com.senla.courses.di.api.annotation.ConfigProperty;
import com.senla.courses.di.api.config.ObjectConfigurator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ConfigPropertyAnnotationObjectConfigurator implements ObjectConfigurator {

    private Object customTypeConverter(Field field, String variable) {
        final String variableType = field.getType().getSimpleName().toLowerCase();
        switch (variableType) {
            case "byte":
                return Byte.valueOf(variable);
            case "short":
                return Short.valueOf(variable);
            case "int":
            case "integer":
                return Integer.valueOf(variable);
            case "long":
                return Long.valueOf(variable);
            case "float":
                return Float.valueOf(variable);
            case "double":
                return Double.valueOf(variable);
            case "boolean":
                return Boolean.valueOf(variable);
            case "char":
            case "character":
                return variable.charAt(0);
            default:
                return variable;
        }
    }

    @Override
    public void configure(Object t, ApplicationContext context) throws IllegalAccessException, FileNotFoundException {
        Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
            if (annotation != null) {
                String path = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(annotation.configName())).getPath();
                try(Stream<String> lines = new BufferedReader(new FileReader(path)).lines()) {
                    Map<String, String> propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
                    String value = annotation.propertyName().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.propertyName());
                    Object convertValue = customTypeConverter(field, value);
                    field.setAccessible(true);
                    field.set(t, convertValue);
                }
            }
        }
    }
}
