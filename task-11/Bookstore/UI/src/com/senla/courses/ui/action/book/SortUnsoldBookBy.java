package com.senla.courses.ui.action.book;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.AbstractAction;

import java.util.logging.Level;

public class SortUnsoldBookBy extends AbstractAction implements IAction {
    private final String index;
    public SortUnsoldBookBy(BookstoreFacade facade, String index) {
        super(facade);
        this.index = index;
    }

    @Override
    public void execute() {
        try {
            if (facade.sortUnsoldBooks(index).isEmpty()) {
                System.out.println("В базе нет залежавшихся книг");
            }
        } catch (ServiceException e) {
            log.log(Level.WARNING, e.getLocalizedMessage(), e);
            System.out.println("Ошибка БД");
        }
    }
}
