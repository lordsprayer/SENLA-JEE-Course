package com.senla.courses;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:application.properties"})
@ComponentScan(basePackages = "com.senla.courses")
public class BookstoreConfiguration {

}
