package com.senla.courses.ui.action;

public class TempAction extends AbstractAction implements IAction{
    @Override
    public void execute() {
        facade.bookDB();
    }
}
