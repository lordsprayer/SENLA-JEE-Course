package com.start;

import com.senla.courses.config.CustomLogger;
import com.senla.courses.ui.menu.MenuController;

public class Starter {

    public static void main(String[] args) {
        new CustomLogger();
        MenuController.getInstance().run();

    }
}
