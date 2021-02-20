package com.senla.courses.ui.menu;

import java.util.Objects;
import java.util.Scanner;

public class MenuController {

    private static MenuController instance;
    private Builder builder = Builder.getInstance();
    private Navigator navigator = Navigator.getInstance();

    private MenuController(){
        builder.getInstance();
        builder.buildMenu();
        navigator.getInstance();
        //navigator.setCurrentMenu(builder.getRootMenu());
        //navigator.printMenu();

    }

    public static MenuController getInstance() {
        return Objects.requireNonNullElse(instance, new MenuController());
    }

    public void run(){
        Scanner scan = new Scanner(System.in);
        navigator.setCurrentMenu(builder.getRootMenu());
        navigator.printMenu();
        Integer index = -1;
        while (!index.equals(0)){
            index = scan.nextInt();
            navigator.navigate(index -1);
            navigator.printMenu();
        }
    }
}
