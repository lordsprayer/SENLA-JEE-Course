package com.senla.courses.dbdao;

import com.senla.courses.Book;
import com.senla.courses.Order;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public interface IBookDao extends GenericDao<Book, Integer> {

    List<Book> getSortBook(String criterion, EntityManager entityManager);
    List<Book> getUnsoldBook(LocalDate date, String criterion, EntityManager entityManager);
    void insertOrder(Book book, Order order, EntityManager entityManager);
}
