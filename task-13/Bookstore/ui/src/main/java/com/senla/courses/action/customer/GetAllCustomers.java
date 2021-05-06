package com.senla.courses.action.customer;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;

import org.apache.logging.log4j.Level;

public class GetAllCustomers extends AbstractAction implements IAction {

    public GetAllCustomers(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try{
            facade.printAllCustomers();
        }  catch (ServiceException e) {
            log.log(Level.WARN, e.getLocalizedMessage(), e);
            System.out.println("Ошибка БД");
        }
    }
}
