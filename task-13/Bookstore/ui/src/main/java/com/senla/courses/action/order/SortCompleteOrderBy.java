package com.senla.courses.action.order;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;

import org.apache.logging.log4j.Level;

public class SortCompleteOrderBy extends AbstractAction implements IAction {
    private final String index;

    public SortCompleteOrderBy(BookstoreFacade facade, String index) {
        super(facade);
        this.index = index;
    }

    @Override
    public void execute() {
        try {
            if (facade.sortCompleteOrders(index, facade.createDate()).isEmpty()) {
                System.out.println("В базе пока нет заказов");
            }
        }  catch (ServiceException e) {
            log.log(Level.WARN, e.getLocalizedMessage(), e);
            System.out.println("Ошибка БД");
        }
    }
}
