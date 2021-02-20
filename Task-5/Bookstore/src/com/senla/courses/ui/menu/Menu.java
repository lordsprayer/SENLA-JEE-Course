package com.senla.courses.ui.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private String name;
    private List<MenuItem> menuItems;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getMenuItems() {
        if(menuItems == null){
            menuItems = new ArrayList<>();
        }
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }


    public void addMenuItem(MenuItem item){
        getMenuItems().add(item);
    }

}
