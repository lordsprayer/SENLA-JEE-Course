package com.senla.courses.util;

import com.senla.courses.model.Book;

import java.util.List;

public class Calculator {

    public static Double calculateTotalCost(List<Book> bookList){
        double totalCost = 0;
        for(Book book : bookList){
            totalCost += book.getCost();
        }
        return totalCost;
    }
}
