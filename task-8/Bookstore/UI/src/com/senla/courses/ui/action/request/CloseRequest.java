package com.senla.courses.ui.action.request;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.validation.IntNumberValidation;

import java.util.logging.Level;

public class CloseRequest extends AbstractAction implements IAction {

    @Override
    public void execute() {
        if (facade.printAllRequests().isEmpty()) {
            System.out.println("Список запросов пуст");
        } else {
            try {
                int id = IntNumberValidation.validation("Ведите id запроса");
                facade.closeRequest((long) id);
                System.out.println("Запрос закрыт");
            } catch (ServiceException e) {
                log.log(Level.WARNING, e.getLocalizedMessage(), e);
                System.out.println("Запроса с таким id не существует");
            }
        }
    }
}
