package com.senla.courses;

import com.senla.courses.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;


public class PropertiesHandler {

    private static final Logger log = LogManager.getLogger(PropertiesHandler.class.getName());
    private static final String FAILED_READ_PROPERTIES_ERROR_MESSAGE = "Failed to read properties";
    private static Properties properties;
    private static final String PROPERTIES_FILE_PATCH ="di/resources/application.properties";

    public static Optional<String> getProperties(String key){
        if(properties == null){
            loadProperties();
        }
        return Optional.ofNullable(properties.getProperty(key));
    }

    private static void loadProperties(){
        try(InputStream inputStream =new FileInputStream(PROPERTIES_FILE_PATCH)){
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e){
            log.log(Level.WARN, FAILED_READ_PROPERTIES_ERROR_MESSAGE + e.getMessage());
            throw new ServiceException(FAILED_READ_PROPERTIES_ERROR_MESSAGE, e);
        }
    }
}
