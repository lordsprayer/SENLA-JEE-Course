package com.senla.courses.ui.action.request;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.validation.IntNumberValidation;

import java.util.logging.Level;

public class CreateRequest extends AbstractAction implements IAction{

    public CreateRequest(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        if (facade.printBooksByAvailability(false).isEmpty()) {
            System.out.println("Нет доступных книг");
        } else {
            try {
                int id = IntNumberValidation.validation("Ввведите id книги");
                facade.createRequest(facade.getBookById(id));
                System.out.println("Запрос создан");
            } catch (ServiceException e) {
                log.log(Level.WARNING, e.getLocalizedMessage(), e);
                System.out.println("Запрос не был создан, так как книги с таким id не существует");
            }
        }
    }
}
