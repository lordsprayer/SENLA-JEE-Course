package com.senla.courses.menu;

import com.senla.courses.api.annotation.Singleton;

@Singleton
public class Navigator {

    private Menu currentMenu;

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
