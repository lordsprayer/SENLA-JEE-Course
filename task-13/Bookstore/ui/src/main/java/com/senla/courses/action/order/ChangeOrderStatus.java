package com.senla.courses.action.order;

import com.senla.courses.exception.DaoException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.action.validation.IntNumberValidation;

import org.apache.logging.log4j.Level;

public class ChangeOrderStatus extends AbstractAction implements IAction {

    public ChangeOrderStatus(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            if(facade.printAllOrders().isEmpty()){
                System.out.println("В база пока нет заказов");
            } else {
                int id = IntNumberValidation.validation("Введите id заказа");
                int status = IntNumberValidation.validation("Выберите статус заказа(1 - новый, 2 - выполнен, 3 - отменён)");
                try{
                    if(facade.getStatus(status)==(null)) {
                        System.out.println("Введён неверный статус заказа");
                    } else {
                        facade.changeOrderStatus(id, facade.getStatus(status));
                        System.out.println("Статус заказа изменён");
                    }
                } catch (DaoException e){
                    log.log(Level.WARN, e.getLocalizedMessage(), e);
                    System.out.println("Заказа с таким id не существует");
                }

            }
        } catch (Exception e) {
            log.log(Level.WARN, e.getLocalizedMessage(), e);
            System.out.println("Ошибка БД");
        }
    }
}
