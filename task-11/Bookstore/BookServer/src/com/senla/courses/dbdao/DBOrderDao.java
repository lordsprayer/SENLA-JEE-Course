package com.senla.courses.dbdao;

import com.senla.courses.api.dbdao.IDBOrderDao;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DBException;
import com.senla.courses.model.Customer;
import com.senla.courses.model.Order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DBOrderDao extends AbstractDBDao<Order, Integer> implements IDBOrderDao {

    private static class PersistOrder extends Order {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT order.id, customer.id, name, surname, phoneNumber, creationDate, completionDate, totalCost, status " +
                "FROM bookstore.order JOIN bookstore.customer on order.idCustomer = customer.id";

    }

    @Override
    public String getSelectWhereQuery() {
        return "SELECT order.id, customer.id, name, surname, phoneNumber, creationDate, completionDate, totalCost, status " +
                "FROM bookstore.order JOIN bookstore.customer on order.idCustomer = customer.id WHERE order.id = ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO bookstore.Order VALUES (null,?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE bookstore.Order " +
                "SET idCustomer = ?, creationDate = ?, completionDate = ?, " +
                "totalCost = ?, status = ? " +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM bookstore.Order WHERE id = ?;";
    }

    @Override
    protected List<Order> parseResultSet(ResultSet rs) {
        ArrayList<Order> result = new ArrayList<>();
        try {
            while (rs.next()) {
                PersistOrder order = new PersistOrder();
                order.setId(rs.getInt("id"));
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                order.setCustomer(customer);
                order.setCreationDate(rs.getDate("creationDate").toLocalDate());
                order.setCompletionDate(rs.getDate("completionDate").toLocalDate());
                order.setTotalCost(rs.getDouble("totalCost"));
                order.setStatus(Order.Status.valueOf(rs.getString("status")));
                result.add(order);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Order object) {
        try {
            int customerId = (object.getCustomer() == null || object.getCustomer().getId() == null) ? -1
                    : object.getCustomer().getId();
            statement.setInt(1, customerId);
            statement.setDate(2, Date.valueOf(object.getCreationDate()));
            statement.setDate(3, Date.valueOf(object.getCompletionDate()));
            statement.setDouble(4, object.getTotalCost());
            statement.setString(5, object.getStatus().toString());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order object) {
        try {
            int customerId = (object.getCustomer() == null || object.getCustomer().getId() == null) ? -1
                    : object.getCustomer().getId();
            statement.setInt(1, customerId);
            statement.setDate(2, Date.valueOf(object.getCreationDate()));
            statement.setDate(3, Date.valueOf(object.getCompletionDate()));
            statement.setDouble(4, object.getTotalCost());
            statement.setString(5, object.getStatus().toString());
            statement.setInt(6, object.getId());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<Order> getSortOrders(String criterion, Connection connection){
        List<Order> list;
        String sql = getSelectQuery();
        sql += " ORDER BY " + criterion;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try ( ResultSet rs = statement.executeQuery()) {
                list = parseResultSet(rs);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return list;
    }

    @Override
    public List<Order> getSortCompleteOrders(String criterion, LocalDate date, Connection connection) {
        List<Order> list;
        String sql = getSelectQuery();
        sql += " WHERE status = 'COMPLETED' AND completionDate > '" + Date.valueOf(date) + "' ORDER BY " + criterion;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet rs = statement.executeQuery()) {
                list = parseResultSet(rs);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return list;
    }
}
