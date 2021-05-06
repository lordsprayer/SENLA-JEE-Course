package com.senla.courses.action.request;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.BookstoreFacade;
import com.senla.courses.action.AbstractAction;
import com.senla.courses.action.IAction;
import com.senla.courses.action.validation.IntNumberValidation;

import org.apache.logging.log4j.Level;

public class DeleteRequest extends AbstractAction implements IAction {

    public DeleteRequest(BookstoreFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        if (facade.printAllRequests().isEmpty()) {
            System.out.println("Список запросов пуст");
        } else {
            try {
                int id = IntNumberValidation.validation("Введите id запроса");
                facade.deleteRequest(id);
                System.out.println("Запрос удален");
            } catch (ServiceException e) {
                log.log(Level.WARN, e.getLocalizedMessage(), e);
                System.out.println("Запроса с таким id не существует");
            }
        }
    }
}
