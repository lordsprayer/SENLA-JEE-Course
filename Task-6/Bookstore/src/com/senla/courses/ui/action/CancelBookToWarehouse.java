package com.senla.courses.ui.action;

import java.util.Scanner;

public class CancelBookToWarehouse extends AbstractAction implements IAction{

    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
        facade.printBooksByAvailability(true);
        System.out.println("Введите id книги, которую хотите списать");
        int id = scan.nextInt();
        facade.cancelBookToWarehouse(facade.getBookById((long)id));
        System.out.println("Книга списана");
        facade.printBook((long)id);
        //scan.close();
    }
}
