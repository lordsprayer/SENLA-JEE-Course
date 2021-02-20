package com.senla.courses.ui.menu;

import com.senla.courses.ui.action.AddBook;

import java.util.Objects;

public class Builder {

    private static Builder instance;// = new Builder();
    private Menu rootMenu;

    //public Builder() {
     //   this.rootMenu = Builder.instance.rootMenu;
   // }

    public static Builder getInstance(){
        return Objects.requireNonNullElse(instance, new Builder());
    }

    public void buildMenu(){
        rootMenu = new Menu();
        rootMenu.addMenuItem(new MenuItem("Работа с книгами", () -> {}, createBookMenu()));
        rootMenu.addMenuItem(new MenuItem("Работа с заказами", () -> {}, createOrderMenu()));
        rootMenu.addMenuItem(new MenuItem("Работа с запросами", () -> {}, createRequestMenu()));
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    private Menu createBookMenu(){
        Menu bookMenu = new Menu();
        bookMenu.addMenuItem(new MenuItem("Добавить книгу", new AddBook(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Редактировать книгу", new AddBook(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Удалить книгу", new AddBook(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Вывести список книг", new AddBook(), rootMenu));
        return bookMenu;
    }

    private Menu createOrderMenu(){
        Menu bookMenu = new Menu();
        bookMenu.addMenuItem(new MenuItem("Добавить книгу2", new AddBook(), rootMenu));
        return bookMenu;
    }

    private Menu createRequestMenu(){
        Menu bookMenu = new Menu();
        bookMenu.addMenuItem(new MenuItem("Добавить книгу3", new AddBook(), rootMenu));
        return bookMenu;
    }

}
