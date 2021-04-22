package com.senla.courses.ui.action.order;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

import java.util.logging.Level;

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
            log.log(Level.WARNING, e.getLocalizedMessage(), e);
            System.out.println("Ошибка БД");
        }
    }
}
