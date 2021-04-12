package com.senla.courses.dbdao;

import com.senla.courses.di.api.annotation.Singleton;
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
    private static final String PROPERTIES_FILE_PATCH ="BookServer/resources/connection.properties";
    private static Properties properties;
    private static Connection connection;
    private static String url;

    public void loadProperties() {
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

    public void openConnection() throws DBException, SQLException {
        connection = DriverManager.getConnection(url, properties);
    }

    public Connection getConnection() throws SQLException {
        loadProperties();
        openConnection();
        return connection;
    }

}
