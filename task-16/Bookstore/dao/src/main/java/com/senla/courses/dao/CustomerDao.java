package com.senla.courses.dao;

import com.senla.courses.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao extends AbstractDao<Customer, Integer> implements ICustomerDao {

    private static final Logger log = LogManager.getLogger(CustomerDao.class);

    @Override
    protected Class<Customer> getClazz() {
        return Customer.class;
    }
}
