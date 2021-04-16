package com.senla.courses.ui.action.order;

import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

import java.sql.SQLException;

public class PrintAllOrders extends AbstractAction implements IAction {

    public PrintAllOrders(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            if(facade.printAllOrders().isEmpty()){
                System.out.println("В базе ещё нет заказов");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
