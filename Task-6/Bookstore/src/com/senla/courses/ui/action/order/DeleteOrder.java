package com.senla.courses.ui.action.order;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class DeleteOrder extends AbstractAction implements IAction {

    @Override
    public void execute() {
        facade.printAllOrders();
        System.out.println("Введите id заказа");
        facade.deleteOrder((long)scan.nextInt());
        System.out.println("Заказ удалён");

    }
}
