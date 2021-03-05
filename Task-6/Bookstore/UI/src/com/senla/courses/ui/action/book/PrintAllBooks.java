package com.senla.courses.ui.action.book;

import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.AbstractAction;

public class PrintAllBooks extends AbstractAction implements IAction {

    @Override
    public void execute() {
        if(facade.getAllBook().isEmpty()){
            System.out.println("В базе пока нет книг");
        } else {
            facade.printAllBook();
        }
    }
}
