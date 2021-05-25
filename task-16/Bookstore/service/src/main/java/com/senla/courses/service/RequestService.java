package com.senla.courses.service;

import com.senla.courses.dao.IBookDao;
import com.senla.courses.dao.IRequestDao;
import com.senla.courses.dto.RequestDto;
import com.senla.courses.exception.DaoException;
import com.senla.courses.mappers.RequestMapper;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;
import com.senla.courses.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RequestService extends ConstantUtil implements IRequestService {

    private static final Logger log = LogManager.getLogger(RequestService.class.getName());
    private final IRequestDao requestDao;
    private final IBookDao bookDao;

    private final RequestMapper mapper = Mappers.getMapper(RequestMapper.class);

    @Override
    public void createRequest(Integer bookId) {
        Book book = bookDao.getByPK(bookId);
        if (book.getAvailability().equals(false)) {
            LocalDate date = LocalDate.now();
            Request request = new Request(book, date);
            try {
                requestDao.persist(request);
            } catch (DaoException e) {
                log.log(Level.WARN, SAVING_ERROR);
                throw e;
            }
            log.log(Level.INFO, "Request created");
        } else {
            log.log(Level.INFO,"The request cannot be created because the book is still available");
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            Request request = requestDao.getByPK(id);
            requestDao.delete(request);
        } catch (DaoException e) {
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void closeRequest(Integer id) {
        Request request = requestDao.getByPK(id);
        request.setStatus(false);
        try {
            requestDao.update(request);
        } catch (DaoException e) {
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public RequestDto getById(Integer id) {
        try{
            Request request = requestDao.getByPK(id);
            return mapper.requestToRequestDto(request);
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<RequestDto> getAll() {
        try {
            List<Request> requests = requestDao.getAll();
            return mapper.requestListToRequestDtoList(requests);
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<RequestDto> getSortRequests() {
        try {
            List<Request> requests = requestDao.getSortRequestsByTitle();
            return mapper.requestListToRequestDtoList(requests);
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<RequestDto> getSortRequestsByBookCount() {
        try {
            List<Request> requests = requestDao.getSortRequestsByBookCount();
            return mapper.requestListToRequestDtoList(requests);
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<RequestDto> getRequests(String sort) {
        if (sort.equals("book")) {
            return getSortRequests();
        } else if (sort.equals("count")) {
            return getSortRequestsByBookCount();
        } else {
            return getAll();
        }
    }
}
