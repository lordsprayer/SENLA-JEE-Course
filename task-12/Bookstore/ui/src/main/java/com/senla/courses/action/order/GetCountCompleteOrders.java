package com.senla.courses.action.order;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;

import java.util.logging.Level;

public class GetCountCompleteOrders extends AbstractAction implements IAction {

    public GetCountCompleteOrders(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            System.out.println("Количество выполненных заказов, удовлетворяющих условиям = " + facade.countCompleteOrders(facade.createDate()));
        }  catch (ServiceException e) {
            log.log(Level.WARNING, e.getLocalizedMessage(), e);
            System.out.println("Ошибка БД");
        }
    }
}
