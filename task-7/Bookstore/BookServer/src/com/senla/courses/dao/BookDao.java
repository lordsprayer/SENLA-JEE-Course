package com.senla.courses.dao;

import com.senla.courses.api.dao.IBookDao;
import com.senla.courses.exception.DaoException;
import com.senla.courses.model.Book;
import com.senla.courses.util.SerializationHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDao implements IBookDao {

    private static final String GET_BY_ID_ERROR_MESSAGE = "Could not find an book by id: %d";
    private static final Logger log = Logger.getLogger(BookDao.class.getName());
    private List<Book> books;

    public BookDao() {
        try{
            books = SerializationHandler.deserialize(Book.class);
        } catch(Exception e){
            books = new ArrayList<>();}
    }


    @Override
    public void save(Book book) {
        try {
            book.setId(books.get(books.size() - 1).getId() + 1L);
        } catch (Exception e){
            book.setId(1L);
        }
        books.add(book);
    }

    @Override
    public void delete(Book book) {
        books.remove(book);
    }

    @Override
    public Book update(Book book) {
        Book book1 = getById(book.getId());
        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        book1.setPublicationYear(book.getPublicationYear());
        book1.setCost(book.getCost());
        book1.setReceiptDate(book.getReceiptDate());
        book1.setAvailability(book.getAvailability());
        book1.setDescription(book.getDescription());
        return book1;
    }

    @Override
    public Book getById(Long id) {
        for(Book book : books){
            if (id.equals(book.getId())){
                return book;
            }
        }
        log.log(Level.WARNING, String.format(GET_BY_ID_ERROR_MESSAGE, id));
        throw new DaoException(String.format(GET_BY_ID_ERROR_MESSAGE, id));
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(books);
    }

    @Override
    public List<Book> getSortBooks(Comparator<Book> comp) {
        List<Book> bookList = new ArrayList<>(books);
        bookList.sort(comp);
        return bookList;
    }

    @Override
    public void saveAll() {
        SerializationHandler.serialize(books);
    }


}
