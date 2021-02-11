package com.senla.courses.dao;

import com.senla.courses.api.dao.IRequestDao;
import com.senla.courses.model.Request;
import com.senla.courses.util.IdGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RequestDao implements IRequestDao {

    private List<Request> requests = new ArrayList<>();

    @Override
    public void save(Request request) {
        request.setId(IdGenerator.GenerateRequestId());
        requests.add(request);
    }

    @Override
    public void delete(Request request) {
        requests.remove(request);
    }

    @Override
    public Request update(Request request) {
        Request request1 = getById(request.getId());
        request1.setBook(request.getBook());
        request1.setDate(request.getDate());
        return request1;
    }

    @Override
    public Request getById(Long id) {
        for(Request request : requests){
            if (id.equals(request.getId())){
                return request;
            }
        }return null;
    }

    @Override
    public List<Request> getAll() {
        List<Request> request1 = new ArrayList<>(requests);
        return request1;
    }

    @Override
    public List<Request> getSortRequests(Comparator<Request> comp) {
        List<Request> requestList = new ArrayList<>(requests);
        requestList.sort(comp);
        return requestList;
    }

}
