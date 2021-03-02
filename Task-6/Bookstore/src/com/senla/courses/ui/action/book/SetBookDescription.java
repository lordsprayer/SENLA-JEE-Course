package com.senla.courses.ui.action.book;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class SetBookDescription extends AbstractAction implements IAction {

    @Override
    public void execute() {
        facade.printAllBook();
        System.out.println("Введите id книги");
        int id = Integer.parseInt(scan.nextLine());
        System.out.println("Введите описание");
        String description = scan.nextLine();
        facade.setBookDescription(facade.getBookById((long)id), description);
        System.out.println("Описание добавлено");

    }
}
