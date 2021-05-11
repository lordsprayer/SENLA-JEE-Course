package com.senla.courses.dbdao;

import com.senla.courses.Book;
import com.senla.courses.Order;

import java.time.LocalDate;
import java.util.List;

public interface IBookDao extends GenericDao<Book, Integer> {

    List<Book> getSortBook(String criterion);
    List<Book> getUnsoldBook(LocalDate date, String criterion);
    void insertOrder(Book book, Order order);
}
