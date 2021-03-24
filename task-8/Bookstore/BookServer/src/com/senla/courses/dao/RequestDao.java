package com.senla.courses.dao;

import com.senla.courses.api.dao.IRequestDao;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DaoException;
import com.senla.courses.model.Request;
import com.senla.courses.util.SerializationHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class RequestDao implements IRequestDao {

    private static final String GET_BY_ID_ERROR_MESSAGE = "Could not find an request by id: %d";
    private static final Logger log = Logger.getLogger(BookDao.class.getName());
    private static List<Request> requests;

    public RequestDao() {
        try{
            requests = SerializationHandler.deserialize(Request.class);
        } catch(Exception e){
            requests = new ArrayList<>();}
    }

    @Override
    public void save(Request request) {
        try {
            request.setId(requests.get(requests.size() - 1).getId() + 1L);
        } catch (Exception e){
            request.setId(1L);
        }
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
        }
        log.log(Level.WARNING, String.format(GET_BY_ID_ERROR_MESSAGE, id));
        throw new DaoException(String.format(GET_BY_ID_ERROR_MESSAGE, id));
    }

    @Override
    public List<Request> getAll() {
        return new ArrayList<>(requests);
    }

    @Override
    public List<Request> getSortRequests() {
        List<Request> requestList = new ArrayList<>(requests);
        requestList.sort(Comparator.comparing(r -> r.getBook().getTitle()));
        return requestList;
    }
}
