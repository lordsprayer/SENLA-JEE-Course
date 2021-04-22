package com.senla.courses.dbdao;

import com.senla.courses.api.dbdao.GenericDao;
import com.senla.courses.api.dbdao.Identified;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DBException;

import java.sql.*;
import java.util.List;

@Singleton
public abstract class AbstractDBDao<T extends Identified<PK>, PK extends Integer> implements GenericDao<T, PK> {

    protected abstract String getSelectQuery();
    protected abstract String getSelectWhereQuery();
    protected abstract String getCreateQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();
    protected abstract List<T> parseResultSet(ResultSet rs);
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object);
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object);

    @Override
    public T getByPK(Integer key, Connection connection){
        List<T> list;
        String sql = getSelectWhereQuery();
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, key);
            try (ResultSet rs = statement.executeQuery()) {
                list = parseResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DBException(e);
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
    public List<T> getAll(Connection connection) {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            try ( ResultSet rs = statement.executeQuery()) {
                list = parseResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return list;
    }

    @Override
    public T persist(T object, Connection connection) {
        if (object.getId() != null) {
            throw new DBException("Object is already persist.");
        }
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DBException("On persist modify more then 1 record: " + count);
            } else {
                try ( ResultSet rs = statement.getGeneratedKeys()) {
                    rs.next();
                    object.setId(rs.getInt(1));
                }
                System.out.println(object);
                return object;
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void update(T object, Connection connection) {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DBException("On update modify more then 1 record: " + count);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void delete(T object, Connection connection) {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, object.getId());
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DBException("On delete modify more then 1 record: " + count);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
