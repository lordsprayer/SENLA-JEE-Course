package com.senla.courses.action.order;

import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;

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
        }  catch (DaoException e) {
            log.log(Level.WARN, e.getLocalizedMessage(), e);
            System.out.println("Ошибка БД");
        }
    }
}
