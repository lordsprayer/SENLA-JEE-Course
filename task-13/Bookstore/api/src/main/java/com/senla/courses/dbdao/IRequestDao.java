package com.senla.courses.dbdao;

import com.senla.courses.Request;

import javax.persistence.EntityManager;
import java.util.List;

public interface IRequestDao extends GenericDao<Request, Integer> {
    List<Request> getSortRequestsByTitle(EntityManager entityManager);
    List<Request> getSortRequestsByBookCount(EntityManager entityManager);
}
