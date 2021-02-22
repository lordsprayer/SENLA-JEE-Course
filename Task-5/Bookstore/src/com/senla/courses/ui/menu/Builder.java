package com.senla.courses.ui.menu;

import com.senla.courses.ui.action.*;

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
        rootMenu.addMenuItem(new MenuItem("Выход", () -> {}, createExitMenu()));
        rootMenu.addMenuItem(new MenuItem("Работа с книгами", () -> {}, createBookMenu()));
        rootMenu.addMenuItem(new MenuItem("Работа с заказами", () -> {}, createOrderMenu()));
        rootMenu.addMenuItem(new MenuItem("Работа с запросами", () -> {}, createRequestMenu()));
        rootMenu.addMenuItem(new MenuItem("Добавить книги в базу", new TempAction(), rootMenu));

    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    private Menu createExitMenu(){
        Menu exitMenu = new Menu();
        exitMenu.addMenuItem(new MenuItem("Спасибо, что воспользовались нашей программой", () -> {}, rootMenu));
        return exitMenu;
    }

    private Menu createBookMenu(){
        Menu bookMenu = new Menu();
        bookMenu.addMenuItem(new MenuItem("Выход", () -> {}, createExitMenu()));
        bookMenu.addMenuItem(new MenuItem("Добавить книгу", new AddBook(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Редактировать книгу", new UpdateBook(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Удалить книгу", new DeleteBook(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Списать книгу со склада", new CancelBookToWarehouse(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Добавить книгу на склад", new AddBookToWarehouse(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Вывести список книг", () -> {}, createSortingBookMenu()));
        return bookMenu;
    }

    private Menu createSortingBookMenu(){
        Menu sortingBookMenu = new Menu();
        sortingBookMenu.addMenuItem(new MenuItem("Выход", () -> {}, createExitMenu()));
        sortingBookMenu.addMenuItem(new MenuItem("Без сортировки", new PrintAllBooks(), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортирвка по названию", new SortBookBy(0), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортирвка по дате издания", new SortBookBy(1), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортирвка по цене", new SortBookBy(2), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортирвка по наличию на складе", new SortBookBy(3), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортирвка залежавшихся книг по дате поступления", new SortUnsoldBookBy(4), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортирвка залежавшихся книг по стоимости", new SortUnsoldBookBy(2), rootMenu));
        return sortingBookMenu;
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
