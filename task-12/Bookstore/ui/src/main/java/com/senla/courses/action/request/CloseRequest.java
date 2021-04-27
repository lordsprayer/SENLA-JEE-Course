package com.senla.courses.action.request;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.action.validation.IntNumberValidation;

import java.util.logging.Level;

public class CloseRequest extends AbstractAction implements IAction {

    public CloseRequest(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        if (facade.printAllRequests().isEmpty()) {
            System.out.println("Список запросов пуст");
        } else {
            try {
                int id = IntNumberValidation.validation("Ведите id запроса");
                facade.closeRequest(id);
                System.out.println("Запрос закрыт");
            } catch (ServiceException e) {
                log.log(Level.WARNING, e.getLocalizedMessage(), e);
                System.out.println("Запроса с таким id не существует");
            }
        }
    }
}