package com.senla.courses.ui.action.order;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.validation.IntNumberValidation;

import java.util.logging.Level;

public class OrderDetails extends AbstractAction implements IAction {

    public OrderDetails(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        int id = IntNumberValidation.validation("Введите id заказа");
        try{
            facade.printOrder((long)id);
        } catch (ServiceException e){
            log.log(Level.WARNING, e.getLocalizedMessage(), e);
            System.out.println("Заказа с таким id не существует");
        }
    }
}
