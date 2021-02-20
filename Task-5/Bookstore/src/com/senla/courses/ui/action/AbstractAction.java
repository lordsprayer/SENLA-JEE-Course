package com.senla.courses.ui.action;

import com.senla.courses.facade.BookstoreFacade;

public abstract class AbstractAction {
    protected BookstoreFacade facade = new BookstoreFacade();
}
