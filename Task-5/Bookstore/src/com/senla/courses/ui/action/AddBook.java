package com.senla.courses.ui.action;

import java.time.LocalDate;
import java.util.Scanner;

public class AddBook extends AbstractAction implements IAction{



    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите название ");
        String title = scan.nextLine();
        System.out.println("Введите автора ");
        String author = scan.nextLine();
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
        Boolean availability = true;
        facade.addBook(title, author, publicationYear, cost, receiptDate, availability);
        System.out.println("Книга добавлена");

    }
}
