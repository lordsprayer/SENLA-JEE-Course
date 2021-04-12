package com.senla.courses.ui.action.customer;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.validation.IntNumberValidation;

import java.util.logging.Level;

public class DeleteCustomer extends AbstractAction implements IAction {

    public DeleteCustomer(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        if (facade.printAllCustomers().isEmpty()) {
            System.out.println("Список покупателей пуст");
        } else {
            try {
                int id = IntNumberValidation.validation("Введите id покупателя");
                facade.deleteCustomer(id);
                System.out.println("Пользователь удален");
            } catch (ServiceException e) {
                log.log(Level.WARNING, e.getLocalizedMessage(), e);
                System.out.println("Пользователя с таким id не существует");
            }
        }
    }
}
