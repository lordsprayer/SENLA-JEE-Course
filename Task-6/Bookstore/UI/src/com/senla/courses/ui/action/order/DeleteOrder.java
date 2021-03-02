package com.senla.courses.ui.action.order;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.validation.IntNumberValidation;

import java.util.logging.Level;

public class DeleteOrder extends AbstractAction implements IAction {

    @Override
    public void execute() {
        if(facade.printAllOrders().isEmpty()){
            System.out.println("В базе ещё нет заказов");
        } else {
            int id = IntNumberValidation.validation("Введите id заказа");
            try{
                facade.deleteOrder((long) id);
                System.out.println("Заказ удалён");
            } catch (ServiceException e){
                log.log(Level.WARNING, e.getLocalizedMessage(), e);
                System.out.println("Заказа с таким id не существует");
            }
        }
    }
}
