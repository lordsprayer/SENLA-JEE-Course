package com.senla.courses.action;

import com.senla.courses.BookstoreFacade;
import java.util.Scanner;

public abstract class AbstractAction {
    
    protected Scanner scan = new Scanner(System.in);
    protected BookstoreFacade facade;

    public AbstractAction(BookstoreFacade facade){
        this.facade = facade;
    }

}
