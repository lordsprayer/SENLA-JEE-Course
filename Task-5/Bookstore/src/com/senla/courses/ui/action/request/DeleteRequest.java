package com.senla.courses.ui.action.request;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class DeleteRequest extends AbstractAction implements IAction {

    @Override
    public void execute() {
        facade.printAllRequests();
        System.out.println("Введите id запроса");
        facade.deleteRequest((long)scan.nextInt());
        System.out.println("Запрос удален");
    }
}
