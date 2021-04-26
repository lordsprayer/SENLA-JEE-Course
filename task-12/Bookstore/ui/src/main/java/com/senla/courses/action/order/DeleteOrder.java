package com.senla.courses.action.order;

import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.action.validation.IntNumberValidation;
import com.senla.courses.exception.ServiceException;

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
        }  catch (ServiceException e) {
            log.log(Level.WARNING, e.getLocalizedMessage(), e);
            System.out.println("Ошибка БД");
        }
    }
}
