package com.senla.courses.ui.action;

import com.senla.courses.facade.BookstoreFacade;

public class SaveAction extends AbstractAction implements IAction{

    public SaveAction(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        facade.saveAll();
    }
}
