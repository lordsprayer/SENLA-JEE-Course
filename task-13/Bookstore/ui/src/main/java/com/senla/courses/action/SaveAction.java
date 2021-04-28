package com.senla.courses.action;

import com.senla.courses.BookstoreFacade;

public class SaveAction extends AbstractAction implements IAction{

    public SaveAction(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
    }
}
