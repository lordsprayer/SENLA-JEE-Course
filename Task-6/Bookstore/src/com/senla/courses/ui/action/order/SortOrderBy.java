package com.senla.courses.ui.action.order;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class SortOrderBy extends AbstractAction implements IAction {
    private final int index;

    public SortOrderBy(int index) {
        this.index = index;
    }

    @Override
    public void execute() {
        facade.sortOrders(facade.createOrderComparators().get(index));
    }
}
