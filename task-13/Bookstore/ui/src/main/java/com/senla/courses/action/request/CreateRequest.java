package com.senla.courses.action.request;

import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.action.validation.IntNumberValidation;
import com.senla.courses.exception.DaoException;
import org.apache.logging.log4j.Level;

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
            } catch (DaoException e) {
                log.log(Level.WARN, e.getLocalizedMessage(), e);
                System.out.println("Запрос не был создан, так как книги с таким id не существует");
            }
        }
    }
}
