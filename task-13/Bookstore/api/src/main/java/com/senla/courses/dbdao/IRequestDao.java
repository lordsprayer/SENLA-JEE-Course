package com.senla.courses.dbdao;

import com.senla.courses.Request;

import java.util.List;

public interface IRequestDao extends GenericDao<Request, Integer> {
    List<Request> getSortRequestsByTitle();
    List<Request> getSortRequestsByBookCount();
}
