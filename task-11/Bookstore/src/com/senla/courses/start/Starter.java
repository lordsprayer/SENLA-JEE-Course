package com.senla.courses.start;

import com.senla.courses.config.CustomLogger;
import com.senla.courses.dbdao.DBConnection;
import com.senla.courses.di.api.annotation.Inject;
import com.senla.courses.di.config.Application;
import com.senla.courses.di.config.ApplicationContext;
import com.senla.courses.ui.menu.MenuController;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Starter {
//    @Inject
//    private static DBConnection dbConnection;
    public static void main(String[] args) {
        new CustomLogger();
        final Logger log = Logger.getLogger(Starter.class.getName());
        try{
            ApplicationContext context = Application.run("com.senla.courses");
            MenuController controller = context.getObject(MenuController.class);
            controller.run();
            //dbConnection.getConnection();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            log.log(Level.SEVERE, "Application configuration error");
        }

    }

}
