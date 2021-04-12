package com.senla.courses.dbdao;

import com.senla.courses.api.dbdao.IDBBookDao;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DBException;
import com.senla.courses.model.Book;

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
    public String getUpdateOrderQuery() {
        return "UPDATE bookstore.Book SET idOrder = ? WHERE id= ?";
    }

    private static class PersistBook extends Book {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, title, author, publicationYear, cost, receiptDate, availability, description " +
                "FROM bookstore.Book";
    }

    @Override
    public String getSelectWhereQuery() {
        return "SELECT id, title, author, publicationYear, cost, receiptDate, availability, description " +
                "FROM bookstore.Book WHERE id = ?";
    }

    @Override
    public String getSelectLastQuery() {
        return "SELECT id, title, author, publicationYear, cost, receiptDate, availability, description " +
                "FROM bookstore.Book WHERE id = last_insert_id()";
    }

    @Override
    public String getCreateQuery() {

        return "INSERT INTO bookstore.Book VALUES (null,?, ?, ?, ?, ?, ?, null, null)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE bookstore.Book " +
                "SET title = ?, author = ?, publicationYear = ?, cost = ?, " +
                "receiptDate = ?, availability = ?, description = ? " +
                "WHERE id= ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM bookstore.Book WHERE id= ?";
    }

    @Override
    protected List<Book> parseResultSet(ResultSet rs) throws DBException {
        ArrayList<Book> result = new ArrayList<>();
        try {
            while (rs.next()) {
                PersistBook book = new PersistBook();
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
    protected void prepareStatementForInsert(PreparedStatement statement, Book object) throws DBException {
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
    protected void prepareStatementForUpdate(PreparedStatement statement, Book object) throws DBException {
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
    public void prepareStatementForInsertOrder(PreparedStatement statement, Book object, Integer orderNumber) throws DBException{
        try {
            statement.setInt(1, orderNumber);
            statement.setInt(2, object.getId());
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<Book> getSortBook(String criterion){
        List<Book> list;
        String sql = getSelectQuery();
        sql += " ORDER BY " + criterion;
        try{
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DBException(e);
        } finally {
            try {
                rs.close();
                statement.close();
                //connection.close();
            } catch (SQLException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
        return list;
    }

    @Override
    public List<Book> getUnsoldBook(LocalDate date, String criterion) {
        List<Book> list;
        String sql = getSelectQuery();
        sql += " WHERE availability = 1 AND receiptDate < '" + date + "' ORDER BY " + criterion;
        try{
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DBException(e);
        } finally {
            try {
                rs.close();
                statement.close();
                //connection.close();
            } catch (SQLException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
        return list;
    }
}
