package com.senla.courses.start;

import com.senla.courses.config.CustomLogger;
import com.senla.courses.di.config.Application;
import com.senla.courses.di.config.ApplicationContext;
import com.senla.courses.ui.menu.MenuController;

import java.lang.reflect.InvocationTargetException;

public class Starter {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        new CustomLogger();
        ApplicationContext context = Application.run("com.senla.courses");
        MenuController controller = context.getObject(MenuController.class);
        controller.run();

    }

}
