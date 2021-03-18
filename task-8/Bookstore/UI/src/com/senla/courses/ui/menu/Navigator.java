package com.senla.courses.ui.menu;

import com.senla.courses.di.api.annotation.Singleton;

import java.util.Objects;

@Singleton
public class Navigator {

    //private static Navigator instance;
    private Menu currentMenu;


//    public static Navigator getInstance(){
//        instance = Objects.requireNonNullElse(instance, new Navigator());
//        return instance;
//    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu(){
        int k=0;
        for(MenuItem item: currentMenu.getMenuItems()){
            System.out.println( k + " "+ item.getTitle());
            k++;
        }
    }

    public void navigate(Integer index){
        MenuItem menuItem = currentMenu.getMenuItems().get(index);
        menuItem.doAction();
        currentMenu = menuItem.getNextMenu();
    }
}
