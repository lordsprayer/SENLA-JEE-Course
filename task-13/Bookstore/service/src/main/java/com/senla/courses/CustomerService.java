package com.senla.courses;

import com.senla.courses.api.annotation.Inject;
import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.dbdao.ICustomerDao;
import com.senla.courses.exception.DaoException;
import com.senla.courses.service.ICustomerService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;

@Singleton
public class CustomerService extends ConstantUtil implements ICustomerService {

    private static final Logger log = LogManager.getLogger (CustomerService.class.getName ());
    @Inject
    private ICustomerDao customerDao;
    @Inject
    private HibernateUtil util;

    @Override
    public void save(Customer customer) {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            customerDao.persist(customer, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public void delete(Customer customer) {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            customerDao.delete(customer, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void update(Customer customer) {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            customerDao.update(customer, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public List<Customer> getAll() {
        EntityManager entityManager = util.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Customer> customers = customerDao.getAll(entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            return customers;
        } catch (DaoException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public Customer getById(Integer id) {
        EntityManager entityManager = util.getEntityManager();
        try{
            entityManager.getTransaction().begin();
            Customer customer = customerDao.getByPK(id, entityManager);
            entityManager.getTransaction().commit();
            entityManager.close();
            return customer;
        } catch (DaoException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }
}
