package com.senla.courses.action.request;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;

import java.util.logging.Level;

public class SortRequestsByTitle extends AbstractAction implements IAction {

    public SortRequestsByTitle(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            facade.sortRequestsByBookTitle();
        } catch (ServiceException e) {
            log.log(Level.WARNING, e.getLocalizedMessage(), e);
            System.out.println("Ошибка БД");
        }
    }
}
