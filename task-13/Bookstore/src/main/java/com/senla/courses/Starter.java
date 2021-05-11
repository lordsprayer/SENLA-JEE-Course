package com.senla.courses;

import com.senla.courses.menu.MenuController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Starter {

    public static void main(String[] args) {
        final Logger log = Logger.getLogger(Starter.class.getName());
        try{
            GenericApplicationContext applicationContext = new AnnotationConfigApplicationContext(BookstoreConfiguration.class);
            MenuController controller = applicationContext.getBean(MenuController.class);
            controller.run();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Application configuration error");
        }

    }

}
