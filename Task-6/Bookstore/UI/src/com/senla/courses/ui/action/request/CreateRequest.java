package com.senla.courses.ui.action.request;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

import java.util.logging.Level;

public class CreateRequest extends AbstractAction implements IAction{

    @Override
    public void execute() {
        try {
            facade.printBooksByAvailability(false);
            System.out.println("Введите id книги");
            facade.createRequest(facade.getBookById((long) scan.nextInt()));
            System.out.println("Запрос создан");
        } catch (Exception e){
            log.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }
}
