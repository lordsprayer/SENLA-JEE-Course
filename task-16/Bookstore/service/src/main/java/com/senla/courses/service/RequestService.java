package com.senla.courses.service;

import com.senla.courses.dao.IRequestDao;
import com.senla.courses.dto.BookDto;
import com.senla.courses.dto.RequestDto;
import com.senla.courses.exception.DaoException;
import com.senla.courses.mappers.BookMapper;
import com.senla.courses.mappers.RequestMapper;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;
import com.senla.courses.util.ConstantUtil;
import com.senla.courses.util.Converter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @Override
    public void createRequest(BookDto bookDto) {
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
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
            System.out.println("Запрос не может быть создан, т.к. книга всё ещё в наличии");
        }
    }

    @Override
    public void delete(RequestDto requestDto) {
        try {
            Request request = RequestMapper.INSTANCE.requestDtoToRequest(requestDto);
            requestDao.delete(request);
        } catch (DaoException e) {
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void closeRequest(RequestDto requestDto) {
        Request request = RequestMapper.INSTANCE.requestDtoToRequest(requestDto);
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
            return RequestMapper.INSTANCE.requestToRequestDto(request);
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<RequestDto> getAll() {
        try {
            List<Request> requests = requestDao.getAll();
            return Converter.convertRequests(requests);
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<RequestDto> getSortRequests() {
        try {
            List<Request> requests = requestDao.getSortRequestsByTitle();
            return Converter.convertRequests(requests);
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public List<RequestDto> getSortRequestsByBookCount() {
        try {
            List<Request> requests = requestDao.getSortRequestsByBookCount();
            return Converter.convertRequests(requests);
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }
}
