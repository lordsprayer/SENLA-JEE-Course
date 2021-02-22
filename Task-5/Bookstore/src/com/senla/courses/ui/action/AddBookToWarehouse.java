package com.senla.courses.ui.action;

import java.util.Scanner;

public class AddBookToWarehouse extends AbstractAction implements IAction{

    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
        facade.printBooksByAvailability(false);
        System.out.println("Введите id книги, которую хотите добавить");
        int id = scan.nextInt();
        facade.addBookToWarehouse(facade.getBookById((long)id));
        System.out.println("Книга добавлена");
        facade.printBook((long)id);
        //scan.close();
    }
}
