package com.senla.courses.service;

import com.senla.courses.api.dbdao.IDBCustomerDao;
import com.senla.courses.api.service.ICustomerService;
import com.senla.courses.dbdao.DBConnection;
import com.senla.courses.di.api.annotation.Inject;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DaoException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.model.Customer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class CustomerService implements ICustomerService {

    private static final Logger log = Logger.getLogger (CustomerService.class.getName ());
    @Inject
    private IDBCustomerDao customerDao;
    @Inject
    private DBConnection dbConnection;

    @Override
    public void save(Customer customer) {
        try {
            customerDao.persist(customer, dbConnection.getConnection());
        } catch (DaoException e) {
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException("Search showed no matches", e);
        }
    }

    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer, dbConnection.getConnection());
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer, dbConnection.getConnection());
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll(dbConnection.getConnection());
    }

    @Override
    public Customer getById(Integer id) {
        try{
            return customerDao.getByPK(id, dbConnection.getConnection());
        } catch (DaoException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException("Search showed no matches", e);
        }
    }
}