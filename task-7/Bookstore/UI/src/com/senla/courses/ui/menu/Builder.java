package com.senla.courses.ui.menu;

import com.senla.courses.ui.action.SaveAction;
import com.senla.courses.ui.action.TempAction;
import com.senla.courses.ui.action.book.*;
import com.senla.courses.ui.action.order.*;
import com.senla.courses.ui.action.request.*;

import java.util.Objects;

public class Builder {

    private static Builder instance;
    private Menu rootMenu;

    public static Builder getInstance(){
        instance = Objects.requireNonNullElse(instance, new Builder());
        return instance;
    }

    public void buildMenu(){
        rootMenu = new Menu();
        rootMenu.addMenuItem(new MenuItem("Выход", new SaveAction(), createExitMenu()));
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
        bookMenu.addMenuItem(new MenuItem("Выход", new SaveAction(), createExitMenu()));
        bookMenu.addMenuItem(new MenuItem("Добавить книгу", new AddBook(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Редактировать книгу", new UpdateBook(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Удалить книгу", new DeleteBook(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Списать книгу со склада", new CancelBookToWarehouse(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Добавить книгу на склад", new AddBookToWarehouse(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Добавить описание книги", new SetBookDescription(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Посмотреть описание книги", new GetBookDescription(), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Вывести список книг", () -> {}, createSortingBookMenu()));
        return bookMenu;
    }

    private Menu createSortingBookMenu(){
        Menu sortingBookMenu = new Menu();
        sortingBookMenu.addMenuItem(new MenuItem("Выход", new SaveAction(), createExitMenu()));
        sortingBookMenu.addMenuItem(new MenuItem("Без сортировки", new PrintAllBooks(), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортировка по названию", new SortBookBy(0), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортировка по дате издания", new SortBookBy(1), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортировка по цене", new SortBookBy(2), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортировка по наличию на складе", new SortBookBy(3), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортировка залежавшихся книг по дате поступления", new SortUnsoldBookBy(4), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортировка залежавшихся книг по стоимости", new SortUnsoldBookBy(2), rootMenu));
        return sortingBookMenu;
    }



    private Menu createOrderMenu(){
        Menu orderMenu = new Menu();
        orderMenu.addMenuItem(new MenuItem("Выход", new SaveAction(), createExitMenu()));
        orderMenu.addMenuItem(new MenuItem("Создать заказ", new CreateOrder(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Удалить заказ", new DeleteOrder(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Изменить статус заказа", new ChangeOrderStatus(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Просмотреть детали заказа", new OrderDetails(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Сумма заработанных средств за период врмени", new GetCountIncome(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Количество выполненных заказов за период времени", new GetCountCompleteOrders(), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Просмотр заказов", () -> {}, createSortingOrderMenu()));
        return orderMenu;
    }

    private Menu createSortingOrderMenu(){
        Menu sortingOrderMenu = new Menu();
        sortingOrderMenu.addMenuItem(new MenuItem("Выход", new SaveAction(), createExitMenu()));
        sortingOrderMenu.addMenuItem(new MenuItem("Без сортировки", new PrintAllOrders(), rootMenu));
        sortingOrderMenu.addMenuItem(new MenuItem("Сортировка по дате исполнения", new SortOrderBy(0), rootMenu));
        sortingOrderMenu.addMenuItem(new MenuItem("Сортировка по цене", new SortOrderBy(1), rootMenu));
        sortingOrderMenu.addMenuItem(new MenuItem("Сортировка по статусу", new SortOrderBy(2), rootMenu));
        sortingOrderMenu.addMenuItem(new MenuItem("Сортировка выполненных заказов за период времени по дате исполнения", new SortCompleteOrderBy(0), rootMenu));
        sortingOrderMenu.addMenuItem(new MenuItem("Сортировка выполненных заказов за период времени по цене", new SortCompleteOrderBy(1), rootMenu));
        return sortingOrderMenu;
    }

    private Menu createRequestMenu(){
        Menu requestMenu = new Menu();
        requestMenu.addMenuItem(new MenuItem("Выход", new SaveAction(), createExitMenu()));
        requestMenu.addMenuItem(new MenuItem("Создать запрос", new CreateRequest(), rootMenu));
        requestMenu.addMenuItem(new MenuItem("Удалить запрос", new DeleteRequest(), rootMenu));
        requestMenu.addMenuItem(new MenuItem("Закрыть запрос", new CloseRequest(), rootMenu));
        requestMenu.addMenuItem(new MenuItem("Список запросов, отсортированный по количеству запросов", new SortRequestsByBookCount(), rootMenu));
        requestMenu.addMenuItem(new MenuItem("Список запросов, отсортированный по названию книги", new SortRequestsByTitle(), rootMenu));
        return requestMenu;
    }

}
