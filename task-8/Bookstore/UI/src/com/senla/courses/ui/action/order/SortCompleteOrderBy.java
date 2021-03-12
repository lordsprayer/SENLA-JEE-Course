package com.senla.courses.ui.action.order;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

import java.time.LocalDate;

public class SortCompleteOrderBy extends AbstractAction implements IAction {
    private final int index;

    public SortCompleteOrderBy(int index) {
        this.index = index;
    }

    @Override
    public void execute() {
        if(facade.sortCompleteOrders(facade.createOrderComparators().get(index), facade.createDate()).isEmpty()){
            System.out.println("В базе пока нет заказов");
        }
    }
}
