package com.senla.courses.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private String name;
    private List<MenuItem> menuItems;

    public Menu() {
    }

    public List<MenuItem> getMenuItems() {
        if(menuItems == null){
            menuItems = new ArrayList<>();
        }
        return menuItems;
    }

    public void addMenuItem(MenuItem item){
        getMenuItems().add(item);
    }

}
