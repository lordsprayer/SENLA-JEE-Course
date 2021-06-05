package com.senla.courses.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({DataSourceConfig.class})
@PropertySource({"classpath:application.properties", "classpath:jdbc.properties" })
@ComponentScan(basePackages = "com.senla.courses")
public class BookstoreConfiguration {

}
