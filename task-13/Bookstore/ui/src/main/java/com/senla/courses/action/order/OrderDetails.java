package com.senla.courses.action.order;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.action.validation.IntNumberValidation;

import org.apache.logging.log4j.Level;

public class OrderDetails extends AbstractAction implements IAction {

    public OrderDetails(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        int id = IntNumberValidation.validation("Введите id заказа");
        try{
            facade.printOrder(id);
        } catch (ServiceException e){
            log.log(Level.WARN, e.getLocalizedMessage(), e);
            System.out.println("Заказа с таким id не существует");
        }
    }
}
