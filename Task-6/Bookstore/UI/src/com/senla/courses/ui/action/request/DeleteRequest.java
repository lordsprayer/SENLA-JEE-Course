package com.senla.courses.ui.action.request;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

import java.util.logging.Level;

public class DeleteRequest extends AbstractAction implements IAction {

    @Override
    public void execute() {
        if (facade.printAllRequests().isEmpty()) {
            System.out.println("Список запросов пуст");
        } else {
            try {
                facade.printAllRequests();
                int id;
                while (true) {
                    System.out.println("Введите id запроса");
                    try {
                        id = Integer.parseInt(scan.next());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный ввод, попробуйте ещё раз");
                    }
                }
                facade.deleteRequest((long) id);
                System.out.println("Запрос удален");
            } catch (ServiceException e) {
                log.log(Level.WARNING, e.getLocalizedMessage(), e);
                System.out.println("Запроса с таким id не существует");
            }
        }
    }
}