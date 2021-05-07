package com.senla.courses.action.customer;

import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;

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
            try {
                facade.saveCustomer(facade.createCustomer(name, surname, phoneNumber));
            } catch (DaoException e) {
                log.log(Level.WARN, e.getLocalizedMessage(), e);
                System.out.println("Ошибка БД");
            }
            System.out.println("Покупатель добавлен");
        } catch (Exception e){
            System.out.println("Ошибка ввода данных");
        }
    }
}
