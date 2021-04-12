package com.senla.courses.ui.action.book;

import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.AbstractAction;

public class SortUnsoldBookBy extends AbstractAction implements IAction {
    private final String index;
    public SortUnsoldBookBy(BookstoreFacade facade, String index) {
        super(facade);
        this.index = index;
    }

    @Override
    public void execute() {
        if(facade.sortUnsoldBooks(index).isEmpty()){
            System.out.println("В базе нет залежавшихся книг");
        }
    }
}
