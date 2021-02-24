package com.senla.courses.ui.menu;

import java.util.Objects;

public class Navigator {

    private static Navigator instance;
    private Menu currentMenu;

    private  Navigator(){

    }

    public static Navigator getInstance(){
        return Objects.requireNonNullElse(instance, new Navigator());
    }

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
