package com.senla.courses.ui.action.order;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

import java.util.ArrayList;
import java.util.List;

public class CreateOrder extends AbstractAction implements IAction {

    @Override
    public void execute() {
        facade.printAllBook();
        System.out.println("Введите id книги");
        List<Long> books = new ArrayList<>();
        int book = scan.nextInt();
        books.add((long)book);
        while (true) {
            System.out.println("Выберите ещё книги либо завершите выбор(нажмите 0): ");
            book = scan.nextInt();
            if (book == 0){
                break;
            }
            books.add((long)book);
        }
        System.out.println("Введите имя покупателя: ");
        String name = scan.next();
        System.out.println("Введите фамилию покупателя: ");
        String surname = scan.next();
        System.out.println("Введите номер телефона покупателя: ");
        String phoneNumber = scan.next();
        facade.createOrder(facade.createCustomer(name, surname, phoneNumber), facade.createBookList(books));
        System.out.println("Заказ создан");
    }
}
