package com.senla.courses;

import com.senla.courses.config.CustomLogger;
import com.senla.courses.config.Application;
import com.senla.courses.config.ApplicationContext;
import com.senla.courses.menu.MenuController;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Starter {

    public static void main(String[] args) {
        new CustomLogger();
        final Logger log = Logger.getLogger(Starter.class.getName());
        try{
            ApplicationContext context = Application.run("com.senla.courses");
            MenuController controller = context.getObject(MenuController.class);
            controller.run();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            log.log(Level.SEVERE, "Application configuration error");
        }

    }

}
