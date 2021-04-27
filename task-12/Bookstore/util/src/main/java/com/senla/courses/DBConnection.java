package com.senla.courses;

import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.exception.DBException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class DBConnection {
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
    private static final String FAILED_READ_PROPERTIES_ERROR_MESSAGE = "Failed to read connection properties";
    private static final String FAILED_GET_CONNECTION_ERROR_MESSAGE = "Failed to get connection";
    private static final String PROPERTIES_FILE_PATCH ="dao/src/main/resources/connection.properties";
    private static Properties properties;
    private Connection connection;
    private static String url;

    public static void loadProperties() {
        try(InputStream inputStream = new FileInputStream(PROPERTIES_FILE_PATCH)) {
            properties = new Properties();
            properties.load(inputStream);
            url = "jdbc:mysql://localhost:3306/" + properties.getProperty("db") +
                    "?useUnicode=true&serverTimezone=UTC";
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, FAILED_READ_PROPERTIES_ERROR_MESSAGE + e.getMessage());
            throw new DBException(FAILED_READ_PROPERTIES_ERROR_MESSAGE, e);
        }
    }

    public void openConnection() {
        try {
            connection = DriverManager.getConnection(url, properties);
        } catch (DBException | SQLException e) {
            LOGGER.log(Level.WARNING, FAILED_GET_CONNECTION_ERROR_MESSAGE + e.getMessage());
            throw new DBException(FAILED_GET_CONNECTION_ERROR_MESSAGE, e);
        }
    }

    public Connection getConnection() {
        loadProperties();
        openConnection();
        return connection;
    }

}
