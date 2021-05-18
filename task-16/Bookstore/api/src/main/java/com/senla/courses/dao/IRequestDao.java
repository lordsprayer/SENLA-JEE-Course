package com.senla.courses.dao;

import com.senla.courses.model.Request;

import java.util.List;

public interface IRequestDao extends GenericDao<Request, Integer> {
    List<Request> getSortRequestsByTitle();
    List<Request> getSortRequestsByBookCount();
}
