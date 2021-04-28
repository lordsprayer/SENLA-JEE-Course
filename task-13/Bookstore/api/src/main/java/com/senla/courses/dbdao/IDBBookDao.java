package com.senla.courses.dbdao;

import com.senla.courses.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;

public interface IDBBookDao extends GenericDao<Book, Integer> {

    String getSelectDescriptionQuery();
    String getSelectBookWhereOrder();
    String getUpdateOrderQuery();
    void prepareStatementForInsertOrder(PreparedStatement statement, Book object,Integer orderNumber);
    List<Book> getSortBook(String criterion, Connection connection);
    List<Book> getUnsoldBook(LocalDate date, String criterion, Connection connection);
    List<Book> getBookByOrder(Integer key, Connection connection);
    void insertOrder(Book book, Integer orderId, Connection connection);
}

