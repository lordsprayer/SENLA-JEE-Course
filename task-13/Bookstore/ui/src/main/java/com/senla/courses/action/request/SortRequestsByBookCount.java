package com.senla.courses.action.request;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;

import org.apache.logging.log4j.Level;

public class SortRequestsByBookCount extends AbstractAction implements IAction {

    public SortRequestsByBookCount(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            facade.sortRequestsByBookCount();
        } catch (ServiceException e) {
            log.log(Level.WARN, e.getLocalizedMessage(), e);
            System.out.println("Ошибка БД");
        }
    }
}
