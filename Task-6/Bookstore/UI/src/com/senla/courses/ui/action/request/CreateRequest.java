package com.senla.courses.ui.action.request;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.logging.Level;

public class CreateRequest extends AbstractAction implements IAction{

    @Override
    public void execute() {
        if (facade.printBooksByAvailability(false).isEmpty()) {
            System.out.println("Нет доступных книг");
        } else {
            try {

                int id;
                while (true) {
                    System.out.println("Введите id книги");
                    try {
                        id = Integer.parseInt(scan.next());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный ввод, попробуйте ещё раз");
                    }
                }
                facade.createRequest(facade.getBookById((long) id));
            } catch (ServiceException e) {
                log.log(Level.WARNING, e.getLocalizedMessage(), e);
                System.out.println("Запрос не был создан, так как книги с таким id не существует");
            }
        }
    }
}
