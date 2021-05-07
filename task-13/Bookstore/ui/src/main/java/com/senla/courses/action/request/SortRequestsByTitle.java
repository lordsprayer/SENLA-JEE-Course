package com.senla.courses.action.request;

import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;

public class SortRequestsByTitle extends AbstractAction implements IAction {

    public SortRequestsByTitle(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            facade.sortRequestsByBookTitle();
        } catch (DaoException e) {
            log.log(Level.WARN, e.getLocalizedMessage(), e);
            System.out.println("Ошибка БД");
        }
    }
}
