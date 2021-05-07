package com.senla.courses.action.order;

import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;

public class GetCountIncome extends AbstractAction implements IAction {

    public GetCountIncome(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            System.out.println("Прибыль за период времени = " + facade.countIncome(facade.createDate()));
        } catch (DaoException e) {
            log.log(Level.WARN, e.getLocalizedMessage(), e);
            System.out.println("Ошибка БД");
        }
    }
}
