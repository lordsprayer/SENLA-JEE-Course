package com.senla.courses.api.dbdao;

import com.senla.courses.model.Book;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;

public interface IDBBookDao extends GenericDao<Book, Integer> {

    String getSelectDescriptionQuery();
    String getUpdateOrderQuery();
    void prepareStatementForInsertOrder(PreparedStatement statement, Book object,Integer orderNumber);
    List<Book> getSortBook(String criterion);
    List<Book> getUnsoldBook(LocalDate date, String criterion);
}

