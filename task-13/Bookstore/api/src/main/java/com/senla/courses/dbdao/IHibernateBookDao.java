package com.senla.courses.dbdao;

import com.senla.courses.Book;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public interface IHibernateBookDao extends HibernateGenericDao<Book, Integer>{

    List<Book> getSortBook(String criterion, EntityManager entityManager);
    List<Book> getUnsoldBook(LocalDate date, String criterion, EntityManager entityManager);
}
