package com.senla.courses.action.book;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.action.validation.IntNumberValidation;

import java.util.logging.Level;

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
            } catch (ServiceException e) {
                log.log(Level.WARNING, e.getLocalizedMessage(), e);
                System.out.println("Введён неверный id");
            }
        }
    }
}
