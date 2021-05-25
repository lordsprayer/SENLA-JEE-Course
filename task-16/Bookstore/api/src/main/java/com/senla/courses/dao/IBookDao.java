package com.senla.courses.dao;

import com.senla.courses.model.Book;
import com.senla.courses.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface IBookDao extends GenericDao<Book, Integer> {

    List<Book> getSortBook(String criterion);
    List<Book> getUnsoldBook(LocalDate date, String criterion);
    void insertOrder(Book book, Order order);
}
