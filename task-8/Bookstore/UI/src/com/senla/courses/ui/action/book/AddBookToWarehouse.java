package com.senla.courses.ui.action.book;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.validation.IntNumberValidation;
import com.senla.courses.util.PropertiesHandler;

import java.util.logging.Level;

public class AddBookToWarehouse extends AbstractAction implements IAction {

    @Override
    public void execute() {
        Boolean prop = PropertiesHandler.getProperties("permit_closing_request")
                .map(Boolean::valueOf).orElse(false);
        if (facade.printBooksByAvailability(false).isEmpty()) {
            System.out.println("Нет книг для добавления");
        } else {
            int id = IntNumberValidation.validation("Введите id книги");
            try {
                facade.addBookToWarehouse(facade.getBookById((long) id), prop);
                System.out.println("Книга добавлена на склад");
                facade.printBook((long) id);
            } catch (ServiceException e) {
                log.log(Level.WARNING, e.getLocalizedMessage(), e);
                System.out.println("Введён неверный id");
            }
        }
    }
}
