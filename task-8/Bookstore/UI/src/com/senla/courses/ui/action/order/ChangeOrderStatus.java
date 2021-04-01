package com.senla.courses.ui.action.order;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.validation.IntNumberValidation;

import java.util.logging.Level;

public class ChangeOrderStatus extends AbstractAction implements IAction {

    public ChangeOrderStatus(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        if(facade.printAllOrders().isEmpty()){
            System.out.println("В база пока нет заказов");
        } else {
            int id = IntNumberValidation.validation("Введите id заказа");
            int status = IntNumberValidation.validation("Выберите статус заказа(1 - новый, 2 - выполнен, 3 - отменён)");
            try{
                facade.changeOrderStatus((long) id, facade.getStatus(status));
                System.out.println("Статус заказа изменён");
            } catch (ServiceException e){
                log.log(Level.WARNING, e.getLocalizedMessage(), e);
                System.out.println("Заказа с таким id не существует");
            } catch (NullPointerException e){
                log.log(Level.WARNING, e.getLocalizedMessage(), e);
                System.out.println("Введён неверный статус заказа");
            }

        }
    }
}
