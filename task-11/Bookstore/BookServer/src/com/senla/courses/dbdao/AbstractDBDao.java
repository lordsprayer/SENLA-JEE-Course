package com.senla.courses.dbdao;

import com.senla.courses.api.dbdao.GenericDao;
import com.senla.courses.api.dbdao.Identified;
import com.senla.courses.di.api.annotation.Inject;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DBException;
import com.senla.courses.model.Book;
import com.senla.courses.model.Customer;
import com.senla.courses.model.Order;
import com.senla.courses.model.Request;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Singleton
public abstract class AbstractDBDao<T extends Identified<PK>, PK extends Integer> implements GenericDao<T, PK> {

    @Inject
    protected DBConnection dbConnection;
    protected Connection connection;
    protected PreparedStatement statement;
    protected ResultSet rs;

    public abstract String getSelectQuery();
    public abstract String getSelectWhereQuery();
    public abstract String getSelectLastQuery();
    public abstract String getCreateQuery();
    public abstract String getUpdateQuery();
    public abstract String getDeleteQuery();
    protected abstract List<T> parseResultSet(ResultSet rs) throws DBException;
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws DBException;
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws DBException;

    @Override
    public T getByPK(Integer key) throws DBException {
        List<T> list;
        String sql = getSelectWhereQuery();
        try{
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, key);
            rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DBException(e);
        } finally {
            try {
                rs.close();
                statement.close();
                //connection.close();
            } catch (SQLException throwables) {
                System.err.println(throwables.getLocalizedMessage());
            }
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new DBException("Received more than one record.");
        }
        return list.iterator().next();
    }

    @Override
    public List<T> getAll() throws DBException {
        List<T> list;
        String sql = getSelectQuery();
        try {
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
            } catch (SQLException throwables) {
                System.err.println(throwables.getLocalizedMessage());
            }
        }
        return list;
    }

    @Override
    public void persist(T object) throws DBException {
        if (object.getId() != null) {
            throw new DBException("Object is already persist.");
        }

        T persistInstance;
        // Добавляем запись
        String sql = getCreateQuery();
        try {
            connection = dbConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DBException("On persist modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
         //Получаем только что вставленную запись
        sql = getSelectLastQuery();
        try {
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            connection.commit();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new DBException("Exception on findByPK new persist data.");
            }
            persistInstance = list.iterator().next();
            System.out.println(persistInstance);
        } catch (Exception e) {
            throw new DBException(e);
        }finally {
            try {
                rs.close();
                statement.close();
                //connection.close();
            } catch (SQLException throwables) {
                System.err.println(throwables.getLocalizedMessage());
            }
        }
    }

    @Override
    public void update(T object) throws DBException {

        String sql = getUpdateQuery();
        try {
            connection = dbConnection.getConnection();
            //connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DBException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DBException(e);
        } finally {
            try {
                rs.close();
                statement.close();
                //connection.close();
            } catch (SQLException throwables) {
                System.err.println(throwables.getLocalizedMessage());
            }
        }
    }

    @Override
    public void delete(T object) throws DBException {
        String sql = getDeleteQuery();
        try {
            connection = dbConnection.getConnection();
            //connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new DBException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DBException("On delete modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        finally {
            try {
                rs.close();
                statement.close();
                //connection.close();
            } catch (SQLException throwables) {
                System.err.println(throwables.getLocalizedMessage());
            }
        }
    }

    private GenericDao getDao(Class<? extends Identified> dtoClass, Connection connection) throws DBException{
        if(dtoClass.equals(Book.class)){
            return new DBBookDao();
        } else if(dtoClass.equals(Customer.class)){
            return new DBCustomerDao();
        } else if(dtoClass.equals(Order.class)) {
            return new DBOrderDao();
        } else if(dtoClass.equals(Request.class)) {
            return new DBRequestDao();
        } else
            throw new DBException("Dao object for " + dtoClass + " not found.");

    }

    protected Identified getDependence(Class<? extends Identified> dtoClass, Serializable pk) throws DBException, SQLException {

        return getDao(dtoClass, dbConnection.getConnection()).getByPK(pk);
    }

}
