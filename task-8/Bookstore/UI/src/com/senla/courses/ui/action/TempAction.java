package com.senla.courses.ui.action;

import com.senla.courses.facade.BookstoreFacade;

public class TempAction extends AbstractAction implements IAction {

    public TempAction(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        facade.bookDB();
    }
}
