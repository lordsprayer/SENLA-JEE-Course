package com.senla.courses.ui.action.order;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.validation.IntNumberValidation;

import java.sql.SQLException;
import java.util.logging.Level;

public class DeleteOrder extends AbstractAction implements IAction {

    public DeleteOrder(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            if(facade.printAllOrders().isEmpty()){
                System.out.println("В базе ещё нет заказов");
            } else {
                int id = IntNumberValidation.validation("Введите id заказа");
                try{
                    facade.deleteOrder(id);
                    System.out.println("Заказ удалён");
                } catch (ServiceException e){
                    log.log(Level.WARNING, e.getLocalizedMessage(), e);
                    System.out.println("Заказа с таким id не существует");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
