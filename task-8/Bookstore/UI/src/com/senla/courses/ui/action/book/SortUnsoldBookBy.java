package com.senla.courses.ui.action.book;

import com.senla.courses.facade.BookstoreFacade;
import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.AbstractAction;
import com.senla.courses.util.PropertiesHandler;

public class SortUnsoldBookBy extends AbstractAction implements IAction {
    private final int index;
    //@ConfigProperty("number_of_months")
    //private final  int prop= 6;
    public SortUnsoldBookBy(BookstoreFacade facade, int index) {
        super(facade);
        this.index = index;
    }

    @Override
    public void execute() {

        Integer prop = PropertiesHandler.getProperties("number_of_months")
                .map(Integer::valueOf).orElse(6);
        if(facade.sortUnsoldBooks(facade.createBookComparators().get(index),prop).isEmpty()){
            System.out.println("В базе нет залежавшихся книг");
        }
    }
}
