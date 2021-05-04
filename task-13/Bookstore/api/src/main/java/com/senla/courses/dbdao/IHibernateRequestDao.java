package com.senla.courses.dbdao;

import com.senla.courses.Request;

import javax.persistence.EntityManager;
import java.util.List;

public interface IHibernateRequestDao extends HibernateGenericDao<Request, Integer>{
    List<Request> getSortRequestsByTitle(EntityManager entityManager);
    List<String> getSortRequestsByBookCount(EntityManager entityManager);
}
