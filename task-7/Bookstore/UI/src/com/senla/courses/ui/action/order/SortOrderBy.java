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
        if(facade.sortOrders(facade.createOrderComparators().get(index)).isEmpty()){
            System.out.println("В базе пока нет заказов");
        }
    }
}
