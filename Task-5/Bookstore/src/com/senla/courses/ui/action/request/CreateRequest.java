package com.senla.courses.ui.action.request;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class CreateRequest extends AbstractAction implements IAction{

    @Override
    public void execute() {
        facade.printBooksByAvailability(false);
        System.out.println("Введите id книги");
        facade.createRequest(facade.getBookById((long)scan.nextInt()));
        System.out.println("Запрос создан");
    }
}
