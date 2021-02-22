package com.senla.courses.ui.action;

import java.time.LocalDate;
import java.util.Scanner;

public class DeleteBook extends AbstractAction implements IAction{

    @Override
    public void execute() {
        facade.printAllBook();
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите id книги ");
        int id = scan.nextInt();
        facade.deleteBook((long)id);
        System.out.println("Книга удалена");
        //facade.printBook((long)id);
        //scan.close();
    }
}
