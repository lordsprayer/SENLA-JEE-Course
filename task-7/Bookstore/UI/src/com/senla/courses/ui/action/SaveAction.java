package com.senla.courses.ui.action;

public class SaveAction extends AbstractAction implements IAction{

    @Override
    public void execute() {
        facade.saveAll();
    }
}
