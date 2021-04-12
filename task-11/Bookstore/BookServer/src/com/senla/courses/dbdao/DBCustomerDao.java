package com.senla.courses.dbdao;

import com.senla.courses.api.dbdao.IDBCustomerDao;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DBException;
import com.senla.courses.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DBCustomerDao extends AbstractDBDao<Customer, Integer> implements IDBCustomerDao {

    private static class PersistCustomer extends Customer {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM bookstore.Customer";
    }

    @Override
    public String getSelectWhereQuery() {
        return "SELECT * FROM bookstore.Customer WHERE id = ?";
    }

    @Override
    public String getSelectLastQuery() {
        return "SELECT * FROM bookstore.Customer WHERE id = last_insert_id()";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO bookstore.Customer VALUES (null,?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE bookstore.Customer " +
                "SET name = ?, surname = ?, phoneNumber = ? " +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM bookstore.Customer WHERE id = ?;";
    }

    @Override
    protected List<Customer> parseResultSet(ResultSet rs) throws DBException {
        ArrayList<Customer> result = new ArrayList<>();
        try {
            while (rs.next()) {
                DBCustomerDao.PersistCustomer customer = new DBCustomerDao.PersistCustomer();
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
    protected void prepareStatementForInsert(PreparedStatement statement, Customer object) throws DBException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getPhoneNumber());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Customer object) throws DBException {
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
