package com.senla.courses.ui.action.customer;

import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

import java.util.Scanner;

public class AddCustomer extends AbstractAction implements IAction {

    public AddCustomer(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Введите имя покупателя: ");
            String name = scan.next();
            System.out.println("Введите фамилию покупателя: ");
            String surname = scan.next();
            System.out.println("Введите номер телефона покупателя: ");
            String phoneNumber = scan.next();
            facade.saveCustomer(facade.createCustomer(name, surname, phoneNumber));
            System.out.println("Покупатель добавлен");
        } catch (Exception e){
            System.out.println("Ошибка ввода данных");
        }
    }
}
