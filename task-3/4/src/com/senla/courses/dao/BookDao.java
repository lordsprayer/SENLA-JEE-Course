package com.senla.courses.dao;

import com.senla.courses.api.dao.IBookDao;
import com.senla.courses.model.Book;
import com.senla.courses.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDao {

    private List<Book> books = new ArrayList<Book>();

    @Override
    public void save(Book book) {
        book.setId(IdGenerator.GenerateBookId());
        books.add(book);
    }

    @Override
    public void delete(Book book) {
        books.remove(book);
    }

    @Override
    public Book update(Book book) {
        Book book1 = getById(book.getId());
        book1.setName(book.getName());
        book1.setAuthor(book.getAuthor());
        ///book1.setPublicationDate(book.getPublicationDate());
        book1.setCost(book.getCost());
        //book1.setReceiptDate(book.getReceiptDate());
        book1.setAvailability(book.getAvailability());
        return book1;
    }

    @Override
    public Book getById(Long id) {
        for(Book book : books){
            if (id.equals(book.getId())){
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books1 = new ArrayList<Book>(books);
        return books1;
    }
}
