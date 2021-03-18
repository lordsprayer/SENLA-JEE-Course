package com.senla.courses.ui.action.order;

import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class GetCountIncome extends AbstractAction implements IAction {

    public GetCountIncome(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        System.out.println("Прибыль за период времени = " + facade.countIncome(facade.createDate()));
    }
}
