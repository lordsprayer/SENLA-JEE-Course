package com.senla.courses.ui.action.customer;

import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class GetAllCustomers extends AbstractAction implements IAction {

    public GetAllCustomers(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        facade.printAllCustomers();
    }
}
