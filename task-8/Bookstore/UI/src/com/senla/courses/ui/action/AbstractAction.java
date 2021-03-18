package com.senla.courses.ui.action;

import com.senla.courses.facade.BookstoreFacade;
import java.util.Scanner;

public abstract class AbstractAction {
    protected BookstoreFacade facade;
    public AbstractAction(BookstoreFacade facade){
        this.facade = facade;
    }
    protected Scanner scan = new Scanner(System.in);
}
