package com.senla.courses.action.book;

import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.action.validation.IntNumberValidation;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;

public class CancelBookToWarehouse extends AbstractAction implements IAction {

    public CancelBookToWarehouse(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        if(facade.printBooksByAvailability(true).isEmpty()){
            System.out.println("Нет книг для удаления");
        } else {
            int id = IntNumberValidation.validation("Введите id книги");
            try {
                facade.cancelBookToWarehouse(facade.getBookById(id));
                System.out.println("Книга списана");
                facade.printBook(id);
            } catch (DaoException e) {
                log.log(Level.WARN, e.getLocalizedMessage(), e);
                System.out.println("Введён неверный id");
            }
        }
    }
}
