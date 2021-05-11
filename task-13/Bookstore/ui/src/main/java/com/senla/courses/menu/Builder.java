package com.senla.courses.menu;

import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.SaveAction;
import com.senla.courses.action.book.*;
import com.senla.courses.action.customer.AddCustomer;
import com.senla.courses.action.customer.DeleteCustomer;
import com.senla.courses.action.customer.GetAllCustomers;
import com.senla.courses.action.customer.UpdateCustomer;
import com.senla.courses.action.order.*;
import com.senla.courses.action.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Builder {

    private Menu rootMenu;
    protected  final BookstoreFacade facade;

    public void buildMenu(){
        rootMenu = new Menu();
        rootMenu.addMenuItem(new MenuItem("Выход", new SaveAction(facade), createExitMenu()));
        rootMenu.addMenuItem(new MenuItem("Работа с книгами", () -> {}, createBookMenu()));
        rootMenu.addMenuItem(new MenuItem("Работа с заказами", () -> {}, createOrderMenu()));
        rootMenu.addMenuItem(new MenuItem("Работа с запросами", () -> {}, createRequestMenu()));
        rootMenu.addMenuItem(new MenuItem("Работа с покупателями", () -> {}, createCustomerMenu()));
        //rootMenu.addMenuItem(new MenuItem("Добавить книги в базу", new TempAction(facade), rootMenu));
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
        bookMenu.addMenuItem(new MenuItem("Выход", new SaveAction(facade), createExitMenu()));
        bookMenu.addMenuItem(new MenuItem("Добавить книгу", new AddBook(facade), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Редактировать книгу", new UpdateBook(facade), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Удалить книгу", new DeleteBook(facade), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Списать книгу со склада", new CancelBookToWarehouse(facade), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Добавить книгу на склад", new AddBookToWarehouse(facade), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Добавить описание книги", new SetBookDescription(facade), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Посмотреть описание книги", new GetBookDescription(facade), rootMenu));
        bookMenu.addMenuItem(new MenuItem("Вывести список книг", () -> {}, createSortingBookMenu()));
        return bookMenu;
    }

    private Menu createSortingBookMenu(){
        Menu sortingBookMenu = new Menu();
        sortingBookMenu.addMenuItem(new MenuItem("Выход", new SaveAction(facade), createExitMenu()));
        sortingBookMenu.addMenuItem(new MenuItem("Без сортировки", new PrintAllBooks(facade), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортировка по названию", new SortBookBy(facade,"title"), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортировка по дате издания", new SortBookBy(facade, "publicationYear"), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортировка по цене", new SortBookBy(facade, "cost"), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортировка по наличию на складе", new SortBookBy(facade, "availability"), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортировка залежавшихся книг по дате поступления", new SortUnsoldBookBy(facade, "receiptDate"), rootMenu));
        sortingBookMenu.addMenuItem(new MenuItem("Сортировка залежавшихся книг по стоимости", new SortUnsoldBookBy(facade, "cost"), rootMenu));
        return sortingBookMenu;
    }



    private Menu createOrderMenu(){
        Menu orderMenu = new Menu();
        orderMenu.addMenuItem(new MenuItem("Выход", new SaveAction(facade), createExitMenu()));
        orderMenu.addMenuItem(new MenuItem("Создать заказ", new CreateOrder(facade), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Удалить заказ", new DeleteOrder(facade), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Изменить статус заказа", new ChangeOrderStatus(facade), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Просмотреть детали заказа", new OrderDetails(facade), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Сумма заработанных средств за период врмени", new GetCountIncome(facade), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Количество выполненных заказов за период времени", new GetCountCompleteOrders(facade), rootMenu));
        orderMenu.addMenuItem(new MenuItem("Просмотр заказов", () -> {}, createSortingOrderMenu()));
        return orderMenu;
    }

    private Menu createSortingOrderMenu(){
        Menu sortingOrderMenu = new Menu();
        sortingOrderMenu.addMenuItem(new MenuItem("Выход", new SaveAction(facade), createExitMenu()));
        sortingOrderMenu.addMenuItem(new MenuItem("Без сортировки", new PrintAllOrders(facade), rootMenu));
        sortingOrderMenu.addMenuItem(new MenuItem("Сортировка по дате исполнения", new SortOrderBy(facade, "completionDate"), rootMenu));
        sortingOrderMenu.addMenuItem(new MenuItem("Сортировка по цене", new SortOrderBy(facade, "totalCost"), rootMenu));
        sortingOrderMenu.addMenuItem(new MenuItem("Сортировка по статусу", new SortOrderBy(facade, "status"), rootMenu));
        sortingOrderMenu.addMenuItem(new MenuItem("Сортировка выполненных заказов за период времени по дате исполнения", new SortCompleteOrderBy(facade, "completionDate"), rootMenu));
        sortingOrderMenu.addMenuItem(new MenuItem("Сортировка выполненных заказов за период времени по цене", new SortCompleteOrderBy(facade, "totalCost"), rootMenu));
        return sortingOrderMenu;
    }

    private Menu createRequestMenu(){
        Menu requestMenu = new Menu();
        requestMenu.addMenuItem(new MenuItem("Выход", new SaveAction(facade), createExitMenu()));
        requestMenu.addMenuItem(new MenuItem("Создать запрос", new CreateRequest(facade), rootMenu));
        requestMenu.addMenuItem(new MenuItem("Удалить запрос", new DeleteRequest(facade), rootMenu));
        requestMenu.addMenuItem(new MenuItem("Закрыть запрос", new CloseRequest(facade), rootMenu));
        requestMenu.addMenuItem(new MenuItem("Список запросов, отсортированный по количеству запросов", new SortRequestsByBookCount(facade), rootMenu));
        requestMenu.addMenuItem(new MenuItem("Список запросов, отсортированный по названию книги", new SortRequestsByTitle(facade), rootMenu));
        return requestMenu;
    }

    private Menu createCustomerMenu(){
        Menu requestMenu = new Menu();
        requestMenu.addMenuItem(new MenuItem("Выход", new SaveAction(facade), createExitMenu()));
        requestMenu.addMenuItem(new MenuItem("Создать покупателя", new AddCustomer(facade), rootMenu));
        requestMenu.addMenuItem(new MenuItem("Удалить покупателя", new DeleteCustomer(facade), rootMenu));
        requestMenu.addMenuItem(new MenuItem("Редактировать покупателя", new UpdateCustomer(facade), rootMenu));
        requestMenu.addMenuItem(new MenuItem("Список покупателей", new GetAllCustomers(facade), rootMenu));
        return requestMenu;
    }

}
