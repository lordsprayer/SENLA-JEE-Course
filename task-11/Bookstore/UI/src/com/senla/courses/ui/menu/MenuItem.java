package com.senla.courses.ui.menu;

import com.senla.courses.ui.action.IAction;


public class MenuItem {

    private final String title;
    private final IAction action;
    private final Menu nextMenu;

    public Menu getNextMenu() {
        return nextMenu;
    }

    public String getTitle() {
        return title;
    }

    public void doAction(){
        action.execute();
    }

    @SuppressWarnings("ClassEscapesDefinedScope")
    public MenuItem(String title, IAction action, Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
    }
}
