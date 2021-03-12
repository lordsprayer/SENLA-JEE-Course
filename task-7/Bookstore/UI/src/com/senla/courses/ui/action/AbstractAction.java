package com.senla.courses.ui.action;

import com.senla.courses.facade.BookstoreFacade;
import java.util.Scanner;

public abstract class AbstractAction {
    protected BookstoreFacade facade = new BookstoreFacade();
    protected Scanner scan = new Scanner(System.in);
}
