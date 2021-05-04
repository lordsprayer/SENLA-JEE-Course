package com.senla.courses;

import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.dbdao.IHibernateCustomerDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class HibernateCustomerDao extends HibernateAbstractDao<Customer, Integer> implements IHibernateCustomerDao {

    private static final Logger log = LogManager.getLogger(HibernateCustomerDao.class);

    @Override
    protected Class<Customer> getClazz() {
        return Customer.class;
    }
}
