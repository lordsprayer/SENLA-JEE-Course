package com.senla.courses.ui.action.request;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class CloseRequest extends AbstractAction implements IAction {

    @Override
    public void execute() {
        facade.printAllRequests();
        System.out.println("Введите id запроса");
        facade.closeRequest((long) scan.nextInt());
        System.out.println("Запрос закрыт");
    }
}
