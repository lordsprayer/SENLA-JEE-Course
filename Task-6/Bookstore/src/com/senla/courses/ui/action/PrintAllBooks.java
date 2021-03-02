package com.senla.courses.ui.action;

public class PrintAllBooks extends AbstractAction implements IAction{

    @Override
    public void execute() {
        facade.printAllBook();
    }
}
