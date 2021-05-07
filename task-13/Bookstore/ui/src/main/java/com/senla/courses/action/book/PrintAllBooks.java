package com.senla.courses.action.book;

import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;

public class PrintAllBooks extends AbstractAction implements IAction {

    public PrintAllBooks(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            if (facade.getAllBook().isEmpty()) {
                System.out.println("В базе пока нет книг");
            } else {
                facade.printAllBook();
            }
        }  catch (DaoException e) {
            log.log(Level.WARN, e.getLocalizedMessage(), e);
            System.out.println("Ошибка БД");
        }
    }
}
