package com.senla.courses.action.customer;

import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.action.validation.IntNumberValidation;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;

public class UpdateCustomer extends AbstractAction implements IAction {

    public UpdateCustomer(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        if(facade.printAllCustomers().isEmpty()){
            System.out.println("В базе пока нет покупателей");
        } else {
            try {
                int id = IntNumberValidation.validation("Введите id покупателя ");
                System.out.println("Введите имя покупателя: ");
                String name = scan.next();
                System.out.println("Введите фамилию покупателя: ");
                String surname = scan.next();
                System.out.println("Введите номер телефона покупателя: ");
                String phoneNumber = scan.next();
                try {
                    facade.updateCustomer(facade.createCustomer(name, surname, phoneNumber), id);
                } catch (DaoException e) {
                    log.log(Level.WARN, e.getLocalizedMessage(), e);
                    System.out.println("Ошибка БД");
                }
                System.out.println("Покупатель обновлен");
                facade.printCustomer(id);
            } catch (Exception e){
                System.out.println("Введены неверные данные");
            }
        }
    }
}
