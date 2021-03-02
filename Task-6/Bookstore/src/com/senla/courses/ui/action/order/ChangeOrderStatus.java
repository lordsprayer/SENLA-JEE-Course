package com.senla.courses.ui.action.order;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class ChangeOrderStatus extends AbstractAction implements IAction {

    @Override
    public void execute() {
        facade.printAllOrders();
        System.out.println("Введите id заказа");
        int id = scan.nextInt();
        System.out.println("Выберите статус заказа(1 - новый, 2 - выполнен, 3 - отменён)");
        facade.changeOrderStatus((long)id, facade.getStatus(scan.nextInt()));
        System.out.println("Статус заказа изменён");
    }
}
