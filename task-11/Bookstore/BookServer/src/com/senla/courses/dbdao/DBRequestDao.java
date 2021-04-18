package com.senla.courses.dbdao;

import com.senla.courses.api.dbdao.IDBRequestDao;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DBException;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DBRequestDao extends AbstractDBDao<Request, Integer> implements IDBRequestDao {

    private static class PersistRequest extends Request {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT request.id, book.id, title, author, publicationYear, cost, receiptDate, availability, date, status " +
                "FROM bookstore.Book JOIN bookstore.Request ON request.idBook = book.id";
    }

    @Override
    public String getSelectWhereQuery() {
        return "SELECT request.id, book.id, title, author, publicationYear, cost, receiptDate, availability, date, status " +
                "FROM bookstore.Book JOIN bookstore.Request ON request.idBook = book.id WHERE request.id = ?";
    }

    @Override
    public String getSelectCountBooksQuery() {
        return "SELECT title, COUNT(book.id) FROM bookstore.book JOIN bookstore.request " +
                "ON request.idBook = book.id GROUP BY book.id ORDER BY COUNT(book.id) DESC";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO bookstore.Request VALUES (null,?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE bookstore.Request " +
                "SET idBook = ?, date = ?, status = ? " +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM bookstore.Request WHERE id = ?;";
    }

    @Override
    protected List<Request> parseResultSet(ResultSet rs) {
        ArrayList<Request> result = new ArrayList<>();
        try {
            while (rs.next()) {
                DBRequestDao.PersistRequest request = new DBRequestDao.PersistRequest();
                request.setId(rs.getInt("id"));
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublicationYear(rs.getInt("publicationYear"));
                book.setCost(rs.getDouble("cost"));
                book.setReceiptDate(rs.getDate("receiptDate").toLocalDate());
                book.setAvailability(rs.getBoolean("availability"));
                request.setBook(book);
                request.setDate(rs.getDate("date").toLocalDate());
                request.setStatus(rs.getBoolean("status"));
                result.add(request);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return result;
    }

    @Override
    public List<String> parseSortResultSet(ResultSet rs) {
        ArrayList<String> result = new ArrayList<>();
        try {
            while (rs.next()) {
                String str = "";
                str += "Название книги " + rs.getString(1) + " Количество запросов " + rs.getInt(2);
                result.add(str);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Request object) {
        try {
            int bookId = (object.getBook() == null || object.getBook().getId() == null) ? -1
                    : object.getBook().getId();
            statement.setInt(1, bookId);
            statement.setDate(2, Date.valueOf(object.getDate().plusDays(1)));
            statement.setBoolean(3, object.getStatus());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Request object) {
        try {
            int bookId = (object.getBook() == null || object.getBook().getId() == null) ? -1
                    : object.getBook().getId();
            statement.setInt(1, bookId);
            statement.setDate(2, Date.valueOf(object.getDate()));
            statement.setBoolean(3, object.getStatus());
            statement.setInt(4, object.getId());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<Request> getSortRequestsByTitle(Connection connection){
        List<Request> list;
        String sql = getSelectQuery();
        sql += " ORDER BY title";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet rs = statement.executeQuery()) {
                list = parseResultSet(rs);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return list;
    }

    @Override
    public List<String> getSortRequestsByBookCount(Connection connection) {
        List<String> list;
        String sql = getSelectCountBooksQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet rs = statement.executeQuery()) {
                list = parseSortResultSet(rs);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return list;
    }
}
