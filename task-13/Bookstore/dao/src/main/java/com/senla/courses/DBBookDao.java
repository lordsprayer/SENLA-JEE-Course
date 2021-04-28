package com.senla.courses;

import com.senla.courses.dbdao.IDBBookDao;
import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.exception.DBException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DBBookDao extends AbstractDBDao<Book, Integer> implements IDBBookDao {

    @Override
    public String getSelectDescriptionQuery() {
        return  "SELECT description FROM bookstore.Book WHERE id = ?";
    }

    @Override
    public String getSelectBookWhereOrder() {
        return "SELECT id, title, author, publicationYear, cost, receiptDate, availability, description " +
                "FROM bookstore.Book WHERE idOrder = ?";
    }

    @Override
    public String getUpdateOrderQuery() {
        return "UPDATE bookstore.Book SET idOrder = ? WHERE id= ?";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT id, title, author, publicationYear, cost, receiptDate, availability, description " +
                "FROM bookstore.Book";
    }

    @Override
    protected String getSelectWhereQuery() {
        return "SELECT id, title, author, publicationYear, cost, receiptDate, availability, description " +
                "FROM bookstore.Book WHERE id = ?";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO bookstore.Book VALUES (null,?, ?, ?, ?, ?, ?, null, null)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE bookstore.Book " +
                "SET title = ?, author = ?, publicationYear = ?, cost = ?, " +
                "receiptDate = ?, availability = ?, description = ? " +
                "WHERE id= ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM bookstore.Book WHERE id= ?";
    }

    @Override
    protected List<Book> parseResultSet(ResultSet rs) {
        ArrayList<Book> result = new ArrayList<>();
        try {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublicationYear(rs.getInt("publicationYear"));
                book.setCost(rs.getDouble("cost"));
                book.setReceiptDate(rs.getDate("receiptDate").toLocalDate());
                book.setAvailability(rs.getBoolean("availability"));
                book.setDescription(rs.getString("description"));
                result.add(book);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Book object) {
        try {
            statement.setString(1, object.getTitle());
            statement.setString(2, object.getAuthor());
            statement.setInt(3, object.getPublicationYear());
            statement.setDouble(4, object.getCost());
            statement.setDate(5, Date.valueOf(object.getReceiptDate().plusDays(1)));
            statement.setBoolean(6, object.getAvailability());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Book object) {
        try {
            statement.setString(1, object.getTitle());
            statement.setString(2, object.getAuthor());
            statement.setInt(3, object.getPublicationYear());
            statement.setDouble(4, object.getCost());
            statement.setDate(5, Date.valueOf(object.getReceiptDate().plusDays(1)));
            statement.setBoolean(6, object.getAvailability());
            statement.setString(7, object.getDescription());
            statement.setInt(8, object.getId());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    @Override
    public void prepareStatementForInsertOrder(PreparedStatement statement, Book object, Integer orderNumber) {
        try {
            statement.setInt(1, orderNumber);
            statement.setInt(2, object.getId());
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<Book> getSortBook(String criterion, Connection connection){
        List<Book> list;
        String sql = getSelectQuery();
        sql += " ORDER BY " + criterion;
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            try ( ResultSet rs = statement.executeQuery()) {
                list = parseResultSet(rs);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return list;
    }

    @Override
    public List<Book> getUnsoldBook(LocalDate date, String criterion, Connection connection) {
        List<Book> list;
        String sql = getSelectQuery();
        sql += " WHERE availability = 1 AND receiptDate < '" + date + "' ORDER BY " + criterion;
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            try (ResultSet rs = statement.executeQuery()) {
                list = parseResultSet(rs);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return list;
    }

    @Override
    public List<Book> getBookByOrder(Integer key, Connection connection) {
        List<Book> list;
        String sql = getSelectBookWhereOrder();
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, key);
            try (ResultSet rs = statement.executeQuery()) {
                list = parseResultSet(rs);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return list;
    }

    @Override
    public void insertOrder(Book book, Integer orderId, Connection connection) {
        String sql = getUpdateOrderQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsertOrder(statement, book, orderId);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DBException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}
