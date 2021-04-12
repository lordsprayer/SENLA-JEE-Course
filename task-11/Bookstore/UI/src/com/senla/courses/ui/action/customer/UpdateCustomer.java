package com.senla.courses.ui.action.customer;

import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.validation.IntNumberValidation;

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
                facade.updateCustomer(facade.createCustomer(name, surname, phoneNumber), id);
                System.out.println("Покупатель обновлен");
                facade.printCustomer(id);
            } catch (Exception e){
                System.out.println("Введены неверные данные");
            }
        }
    }
}
