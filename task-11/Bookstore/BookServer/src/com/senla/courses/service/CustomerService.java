package com.senla.courses.service;

import com.senla.courses.api.dbdao.IDBCustomerDao;
import com.senla.courses.api.service.ICustomerService;
import com.senla.courses.dbdao.DBConnection;
import com.senla.courses.di.api.annotation.Inject;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DBException;
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
        } catch (DBException e) {
            log.log(Level.WARNING, "Error when saving an object");
            throw new ServiceException("Error when saving an object", e);
        }
    }

    @Override
    public void delete(Customer customer) {
        try {
            customerDao.delete(customer, dbConnection.getConnection());
        } catch (DBException e) {
            log.log(Level.WARNING, "Error when deleting an object");
            throw new ServiceException("Error when deleting an object", e);
        }
    }

    @Override
    public void update(Customer customer) {
        try {
            customerDao.update(customer, dbConnection.getConnection());
        } catch (DBException e) {
            log.log(Level.WARNING, "Error when updating an object");
            throw new ServiceException("Error when updating an object", e);
        }
    }

    @Override
    public List<Customer> getAll() {
        try {
            return customerDao.getAll(dbConnection.getConnection());
        } catch (DBException e) {
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException("Search showed no matches", e);
        }
    }

    @Override
    public Customer getById(Integer id) {
        try{
            return customerDao.getByPK(id, dbConnection.getConnection());
        } catch (DBException e){
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException("Search showed no matches", e);
        }
    }
}
