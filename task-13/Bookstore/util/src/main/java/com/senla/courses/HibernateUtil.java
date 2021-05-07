package com.senla.courses;

import com.senla.courses.api.annotation.Singleton;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
public class HibernateUtil {

    private static final Logger log = LogManager.getLogger(HibernateUtil.class.getName());

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
            log.log(Level.ERROR, "JPA error");
            throw new ExceptionInInitializerError(ex);
        }
    }
}
