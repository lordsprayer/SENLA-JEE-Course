package com.senla.courses.dbdao;

import com.senla.courses.api.dbdao.IDBOrderDao;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DBException;
import com.senla.courses.model.Customer;
import com.senla.courses.model.Order;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        return "SELECT id, idCustomer, creationDate, completionDate, totalCost, status " +
                "FROM bookstore.Order";
    }

    @Override
    public String getSelectWhereQuery() {
        return null;
    }

    @Override
    public String getSelectLastQuery() {
        return null;
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO bookstore.Order VALUES (null,?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE bookstore.Order " +
                "SET idCustomer = ?, creationDate = ?, completionDate = ? " +
                "totalCost = ?, status = ? " +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM bookstore.Order WHERE id = ?;";
    }

    @Override
    protected List<Order> parseResultSet(ResultSet rs) throws DBException {
        ArrayList<Order> result = new ArrayList<>();
        try {
            while (rs.next()) {
                DBOrderDao.PersistOrder order = new DBOrderDao.PersistOrder();
                order.setId(rs.getInt("id"));
                order.setCustomer((Customer) getDependence(Customer.class, rs.getInt("idCustomer")));
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
    protected void prepareStatementForInsert(PreparedStatement statement, Order object) throws DBException {
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
    protected void prepareStatementForUpdate(PreparedStatement statement, Order object) throws DBException {
        try {
            int customerId = (object.getCustomer() == null || object.getCustomer().getId() == null) ? -1
                    : object.getCustomer().getId();
            statement.setInt(1, object.getId());
            statement.setInt(2, customerId);
            statement.setDate(3, Date.valueOf(object.getCreationDate()));
            statement.setDate(4, Date.valueOf(object.getCompletionDate()));
            statement.setDouble(5, object.getTotalCost());
            statement.setString(6, object.getStatus().toString());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public List<Order> getSortOrders(String criterion){
        List<Order> list;
        String sql = getSelectQuery();
        sql += " ORDER BY " + criterion;
        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DBException(e);
        }
        return list;
    }

    @Override
    public Order getByPK(Integer key) throws DBException {
        return null;
    }
}
