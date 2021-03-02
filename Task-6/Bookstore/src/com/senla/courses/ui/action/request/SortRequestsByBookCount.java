package com.senla.courses.ui.action.request;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class SortRequestsByBookCount extends AbstractAction implements IAction {

    @Override
    public void execute() {
        facade.sortRequestsByBookCount();
    }
}
