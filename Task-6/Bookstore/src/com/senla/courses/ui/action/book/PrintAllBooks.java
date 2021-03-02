package com.senla.courses.ui.action.book;

import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.AbstractAction;

public class PrintAllBooks extends AbstractAction implements IAction {

    @Override
    public void execute() {
        facade.printAllBook();
    }
}
