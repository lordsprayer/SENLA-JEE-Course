package com.senla.courses.ui.menu;

import java.util.Objects;
import java.util.Scanner;

public class MenuController {

    private static MenuController instance;
    private final Builder builder = Builder.getInstance();
    private final Navigator navigator = Navigator.getInstance();

    private MenuController(){
        Builder.getInstance();
        builder.buildMenu();
        Navigator.getInstance();

    }

    public static MenuController getInstance() {

        instance = Objects.requireNonNullElse(instance, new MenuController());
        return instance;
    }

    public void run(){
        Scanner scan = new Scanner(System.in);
        navigator.setCurrentMenu(builder.getRootMenu());
        navigator.printMenu();
        Integer index = -1;
        while (!index.equals(0)){
            index = scan.nextInt();
            navigator.navigate(index);
            navigator.printMenu();
        }
        scan.close();
    }
}
