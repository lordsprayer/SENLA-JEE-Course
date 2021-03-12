package com.senla.courses.api.dao;

import com.senla.courses.model.Request;

import java.util.List;

public interface IRequestDao {


    void save(Request request);
    void delete(Request request);
    Request update(Request request);
    Request getById(Long id);
    List<Request> getAll();
    List<Request> getSortRequests();
    void saveAll();


}
