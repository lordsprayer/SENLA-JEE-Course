package com.senla.courses.ui.action.customer;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

import java.util.logging.Level;

public class GetAllCustomers extends AbstractAction implements IAction {

    public GetAllCustomers(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try{
            facade.printAllCustomers();
        }  catch (ServiceException e) {
            log.log(Level.WARNING, e.getLocalizedMessage(), e);
            System.out.println("Ошибка БД");
        }
    }
}
