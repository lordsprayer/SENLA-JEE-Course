package com.senla.courses.ui.action.order;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

import java.util.logging.Level;

public class GetCountIncome extends AbstractAction implements IAction {

    public GetCountIncome(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            System.out.println("Прибыль за период времени = " + facade.countIncome(facade.createDate()));
        } catch (ServiceException e) {
            log.log(Level.WARNING, e.getLocalizedMessage(), e);
            System.out.println("Ошибка БД");
        }
    }
}
