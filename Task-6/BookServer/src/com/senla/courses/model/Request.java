package com.senla.courses.model;

import java.time.LocalDate;


public class Request extends AId implements Comparable<Request>{
    private Book book;
    private LocalDate date;
    private Boolean status;

    public Request(Book book, LocalDate date) {
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
