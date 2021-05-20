package com.senla.courses.service;

import com.senla.courses.dao.IBookDao;
import com.senla.courses.dao.IRequestDao;
import com.senla.courses.dto.BookDto;
import com.senla.courses.exception.DaoException;
import com.senla.courses.mappers.BookMapper;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;
import com.senla.courses.util.ConstantUtil;
import com.senla.courses.util.Converter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService extends ConstantUtil implements IBookService {

    private static final Logger log = LogManager.getLogger(BookService.class);
    private final IRequestDao requestDao;
    private final IBookDao bookDao;
    @Value("${number_of_months:6}")
    private Integer months;
    @Value("${permit_closing_request:true}")
    private Boolean permit;

    @Override
    public List<BookDto> getAll() {
        try {
            List<Book> books = bookDao.getAll();
            return Converter.convertBooks(books);
        } catch (DaoException e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public BookDto getById(Integer id) {
        try{
            Book book = bookDao.getByPK(id);
            return BookMapper.INSTANCE.bookToBookDto(book);
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public void save(BookDto bookDto) {
        try {
            Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
            bookDao.persist(book);
        } catch (DaoException e){
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public void delete(BookDto bookDto) {
        try {
            Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
            bookDao.delete(book);
        }  catch (DaoException e){
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void update(BookDto bookDto) {
        try {
            Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
            bookDao.update(book);
        } catch (DaoException e){
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public void cancelBook(BookDto bookDto) {
        try {
            Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
            book.setAvailability(false);
            bookDao.update(book);
        } catch (DaoException e){
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public void addBook(BookDto bookDto) {
        try {
            Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
            book.setAvailability(true);
            bookDao.update(book);
            if (permit) {
                List<Request> requests = requestDao.getAll();
                for (Request request : requests) {
                    if (request.getBook().equals(book)) {
                        request.setStatus(false);
                        requestDao.update(request);
                    }
                }
            } else {
                log.log(Level.INFO, "Automatic closing of requests is prohibited");
            }
        } catch (DaoException e) {
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }


    @Override
    public List<BookDto> unsoldBook(String criterion) {
        LocalDate date = LocalDate.now().minusMonths(months);
        try {
            List<Book> books = bookDao.getUnsoldBook(date,criterion);
            return Converter.convertBooks(books);
        } catch (DaoException e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public String getDescription(BookDto bookDto) {
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
        System.out.println(book.getDescription());
        return book.getDescription();
    }

    @Override
    public List<BookDto> getSortBooks(String criterion) {
        try {
            List<Book> books = bookDao.getSortBook(criterion);
            return Converter.convertBooks(books);
        } catch (DaoException e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }
}
