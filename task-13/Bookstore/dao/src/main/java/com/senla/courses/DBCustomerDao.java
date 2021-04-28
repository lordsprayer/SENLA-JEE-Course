package com.senla.courses;

import com.senla.courses.dbdao.IDBCustomerDao;
import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.exception.DBException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DBCustomerDao extends AbstractDBDao<Customer, Integer> implements IDBCustomerDao {

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM bookstore.Customer";
    }

    @Override
    protected String getSelectWhereQuery() {
        return "SELECT * FROM bookstore.Customer WHERE id = ?";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO bookstore.Customer VALUES (null,?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE bookstore.Customer " +
                "SET name = ?, surname = ?, phoneNumber = ? " +
                "WHERE id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM bookstore.Customer WHERE id = ?;";
    }

    @Override
    protected List<Customer> parseResultSet(ResultSet rs) {
        ArrayList<Customer> result = new ArrayList<>();
        try {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                result.add(customer);
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            throw new DBException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Customer object) {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getPhoneNumber());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Customer object) {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getPhoneNumber());
            statement.setInt(4, object.getId());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}
