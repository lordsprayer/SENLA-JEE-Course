package com.senla.courses.ui.action;

import java.time.LocalDate;
import java.util.Scanner;

public class UpdateBook extends AbstractAction implements IAction{

    @Override
    public void execute() {
        facade.printAllBook();
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите id книги ");
        int id = scan.nextInt();
        System.out.println("Введите название ");
        String title = scan.next();
        System.out.println("Введите автора ");
        String author = scan.next();
        System.out.println("Введите год публикации ");
        Integer publicationYear = scan.nextInt();
        System.out.println("Введите стоимость ");
        Double cost = scan.nextDouble();
        System.out.println("Введите дату поступления ");
        System.out.println("Введите год ");
        int year = scan.nextInt();
        System.out.println("Введите месяц ");
        int month = scan.nextInt();
        System.out.println("Введите день ");
        int day = scan.nextInt();
        LocalDate receiptDate = LocalDate.of(year, month,day);
        facade.updateBook(facade.createBook(title, author, publicationYear, cost, receiptDate, true), (long)id);
        System.out.println("Книга обновлена");
        facade.printBook((long)id);
        scan.close();
    }
}
