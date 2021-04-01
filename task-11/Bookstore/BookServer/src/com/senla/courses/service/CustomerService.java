package com.senla.courses.service;

import com.senla.courses.api.dao.ICustomerDao;
import com.senla.courses.api.service.ICustomerService;
import com.senla.courses.di.api.annotation.Inject;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DaoException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.model.Customer;
import com.senla.courses.util.SerializationHandler;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class CustomerService implements ICustomerService {

    private static final Logger log = Logger.getLogger (CustomerService.class.getName ());
    @Inject
    private static ICustomerDao customerDao;

    @Override
    public void save(Customer customer) {
        try {
            customerDao.save(customer);
        } catch (DaoException e) {
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException("Search showed no matches", e);
        }
    }

    @Override
    public void saveAll() {
        SerializationHandler.serialize(customerDao.getAll());
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }
}
