package com.senla.courses.ui.action.order;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class PrintAllOrders extends AbstractAction implements IAction {

    @Override
    public void execute() {
        if(facade.printAllOrders().isEmpty()){
            System.out.println("В базе ещё нет заказов");
        }
    }
}
