package com.senla.courses.service;

import com.senla.courses.dao.IBookDao;
import com.senla.courses.dao.IRequestDao;
import com.senla.courses.dto.BookDto;
import com.senla.courses.exception.DaoException;
import com.senla.courses.mappers.BookMapper;
import com.senla.courses.model.Book;
import com.senla.courses.model.Request;
import com.senla.courses.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
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
    private final BookMapper mapper= Mappers.getMapper(BookMapper.class);

    public void setPermit(Boolean permit) {
        this.permit = permit;
    }

    @Override
    public List<BookDto> getAll() {
        try {
            List<Book> books = bookDao.getAll();
            return mapper.bookListToBookDtoList(books);
        } catch (DaoException e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public BookDto getById(Integer id) {
        try{
            Book book = bookDao.getByPK(id);
            return mapper.bookToBookDto(book);
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public void save(BookDto bookDto) {
        try {
            Book book = mapper.bookDtoToBook(bookDto);
            bookDao.persist(book);
        } catch (DaoException e){
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            Book book = bookDao.getByPK(id);
            bookDao.delete(book);
        }  catch (DaoException e){
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void update(BookDto bookDto) {
        try {
            Book book = bookDao.getByPK(bookDto.getId());
            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setPublicationYear(bookDto.getPublicationYear());
            book.setAvailability(bookDto.getAvailability());
            book.setCost(bookDto.getCost());
            book.setReceiptDate(bookDto.getReceiptDate());
            book.setDescription(bookDto.getDescription());
            bookDao.update(book);
        } catch (DaoException e){
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public void cancelBook(Integer id) {
        try {
            Book book = bookDao.getByPK(id);
            book.setAvailability(false);
            bookDao.update(book);
        } catch (DaoException e){
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public void addBook(Integer id) {
        try {
            Book book = bookDao.getByPK(id);
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
            return mapper.bookListToBookDtoList(books);
        } catch (DaoException e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }


    @Override
    public List<BookDto> getSortBooks(String criterion) {
        try {
            List<Book> books = bookDao.getSortBook(criterion);
            return mapper.bookListToBookDtoList(books);
        } catch (DaoException e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public void addOrDeleteBook(Integer id, Boolean availability) {
        if(availability) {
            addBook(id);
        } else {
            cancelBook(id);
        }
    }
}
