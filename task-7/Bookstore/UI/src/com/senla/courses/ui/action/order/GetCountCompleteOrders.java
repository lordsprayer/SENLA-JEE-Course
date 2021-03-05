package com.senla.courses.ui.action.order;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class GetCountCompleteOrders extends AbstractAction implements IAction {

    @Override
    public void execute() {
        System.out.println("Количество выполненных заказов, удовлетворяющих условиям = " + facade.countCompleteOrders(facade.createDate()));
    }
}
