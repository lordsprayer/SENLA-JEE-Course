package com.senla.courses.ui.action.request;

import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class SortRequestsByTitle extends AbstractAction implements IAction {

    public SortRequestsByTitle(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        facade.sortRequestsByBookTitle();
    }
}
