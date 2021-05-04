package com.senla.courses;

import com.senla.courses.api.annotation.Inject;
import com.senla.courses.api.annotation.Singleton;
import com.senla.courses.dbdao.IHibernateCustomerDao;
import com.senla.courses.exception.DBException;
import com.senla.courses.exception.ServiceException;
import com.senla.courses.service.ICustomerService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class CustomerService implements ICustomerService {

    private static final Logger log = Logger.getLogger (CustomerService.class.getName ());
    @Inject
    private IHibernateCustomerDao customerDao;
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
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Error when saving an object");
            throw new ServiceException("Error when saving an object", e);
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
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Error when deleting an object");
            throw new ServiceException("Error when deleting an object", e);
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
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Error when updating an object");
            throw new ServiceException("Error when updating an object", e);
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
        } catch (DBException e) {
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException("Search showed no matches", e);
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
        } catch (DBException e){
            entityManager.getTransaction().rollback();
            log.log(Level.WARNING, "Search showed no matches");
            throw new ServiceException("Search showed no matches", e);
        }
    }
}
