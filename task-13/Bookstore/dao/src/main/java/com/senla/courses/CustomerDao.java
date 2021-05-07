package com.senla.courses;

import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.dbdao.ICustomerDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class CustomerDao extends AbstractDao<Customer, Integer> implements ICustomerDao {

    private static final Logger log = LogManager.getLogger(CustomerDao.class);

    @Override
    protected Class<Customer> getClazz() {
        return Customer.class;
    }
}
