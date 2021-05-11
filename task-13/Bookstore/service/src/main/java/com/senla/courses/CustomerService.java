package com.senla.courses;

import com.senla.courses.dbdao.ICustomerDao;
import com.senla.courses.exception.DaoException;
import com.senla.courses.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService extends ConstantUtil implements ICustomerService {

    private static final Logger log = LogManager.getLogger (CustomerService.class.getName ());
    private final ICustomerDao customerDao;

    @Override
    public void save(Customer customer) {
        try {
            customerDao.persist(customer);
        } catch (DaoException e) {
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public void delete(Customer customer) {
        try {
            customerDao.delete(customer);
        } catch (DaoException e) {
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void update(Customer customer) {
        try {
            customerDao.update(customer);
        } catch (DaoException e) {
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public List<Customer> getAll() {
        try {
            return customerDao.getAll();
        } catch (DaoException e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public Customer getById(Integer id) {
        try{
            return customerDao.getByPK(id);
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }
}
