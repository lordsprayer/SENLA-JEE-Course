package com.senla.courses;

import com.senla.courses.api.annotation.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
public class HibernateUtil {

    private  EntityManager entityManager;

    public  EntityManager getEntityManager() {
        init();
        return entityManager;
    }

    private void init() {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("Bookstore");
            entityManager = factory.createEntityManager();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void begin() {
        getEntityManager().getTransaction().begin();
    }

    public void commit() {
        getEntityManager().getTransaction().commit();
    }

    public void rollback() {
        getEntityManager().getTransaction().rollback();
    }
}
