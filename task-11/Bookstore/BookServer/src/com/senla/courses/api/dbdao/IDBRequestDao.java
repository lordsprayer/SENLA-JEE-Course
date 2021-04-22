package com.senla.courses.api.dbdao;

import com.senla.courses.model.Book;
import com.senla.courses.model.Request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public interface IDBRequestDao extends GenericDao<Request, Integer>{
    String getSelectCountBooksQuery();
    List<Request> getSortRequestsByTitle(Connection connection);
    List<String> getSortRequestsByBookCount(Connection connection);
    List<String> parseSortResultSet(ResultSet rs);
}
