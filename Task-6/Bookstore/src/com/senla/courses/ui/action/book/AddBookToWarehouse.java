package com.senla.courses.ui.action.book;

import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.ui.action.IAction;

public class AddBookToWarehouse extends AbstractAction implements IAction {

    @Override
    public void execute() {
        facade.printBooksByAvailability(false);
        System.out.println("Введите id книги");
        int id = scan.nextInt();
        facade.addBookToWarehouse(facade.getBookById((long)id));
        System.out.println("Книга добавлена");
        facade.printBook((long)id);
    }
}
