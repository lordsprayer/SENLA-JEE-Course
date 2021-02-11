package com.senla.courses.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Request extends AId implements Comparable<Request>{
    private Book book;
    private Date date;
    private Boolean status;

    public Request(Book book, Date date) {
        this.book = book;
        this.date = date;
        this.status = true;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return "Request #" + getId() + " Book { id = " + book.getId() + " name = " +
                book.getName() + " author = " + book.getAuthor() +
                " price = " + book.getCost() + " availability = " +book.getAvailability() +
                " date = " + date + " + status = " + status + "}";
    }

    @Override
    public int compareTo(Request request) {
        return (int)(this.id - request.id);
    }

    //компаратор по названию книги
    public static Comparator<Request> NameComparator = Comparator.comparing(r -> r.book.getName());
    //компаратор по количеству запросов на книгу


}
