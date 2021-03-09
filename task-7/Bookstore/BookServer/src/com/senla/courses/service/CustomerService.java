package com.senla.courses.service;

import com.senla.courses.api.dao.ICustomerDao;
import com.senla.courses.api.service.ICustomerService;
import com.senla.courses.exception.DaoException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.model.Customer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerService implements ICustomerService {

    private static final Logger log = Logger.getLogger (CustomerService.class.getName ());
    private static ICustomerDao customerDao;

    public CustomerService(ICustomerDao customerDao) {
        CustomerService.customerDao = customerDao;
    }

    @Override
    public void save(Customer customer) {
        try {
            customerDao.save(customer);
        } catch (DaoException e) {
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException("Search showed no matches");
        }
    }

    @Override
    public void saveAll() {
        customerDao.saveAll();
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }
}
