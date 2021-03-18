package com.senla.courses.ui.menu;

import com.senla.courses.di.api.annotation.Inject;
import com.senla.courses.di.api.annotation.Singleton;

import java.util.Objects;
import java.util.Scanner;

@Singleton
public class MenuController {

    //private static MenuController instance;
    @Inject
    private Builder builder;
    @Inject
    private Navigator navigator;

//    public MenuController(){
//        //Builder.getInstance();
//
//        //Navigator.getInstance();
//
//    }

//    public static MenuController getInstance() {
//
//        instance = Objects.requireNonNullElse(instance, new MenuController());
//        return instance;
//    }


    public void run(){
        Scanner scan = new Scanner(System.in);
        builder.buildMenu();
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
