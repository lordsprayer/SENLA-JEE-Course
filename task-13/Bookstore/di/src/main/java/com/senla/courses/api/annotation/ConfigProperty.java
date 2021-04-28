package com.senla.courses.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface ConfigProperty {
    String configName() default "application.properties";
    String propertyName() default "";
    String type() default "";
}
