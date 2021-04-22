package com.senla.courses.model;

import com.senla.courses.api.dbdao.Identified;

import java.time.LocalDate;


public class Request implements Identified<Integer>, Comparable<Request> {
    private Integer id = null;
    private Book book;
    private LocalDate date;
    private Boolean status;

    public Request(Book book, LocalDate date) {
        this.book = book;
        this.date = date;
        this.status = true;
    }

    public Request(){

    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
        return "Запрос #" + getId() + " [Книга " +
                book.getTitle() + " Автор " + book.getAuthor() +
                " Стоимость " + book.getCost() +
                " Дата создания " + date +  " Статус " + getStatus() + "]";
    }

    @Override
    public int compareTo(Request request) {
        return (int)(this.id - request.id);
    }

}
