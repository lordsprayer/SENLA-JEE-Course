package com.senla.courses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Request implements Identified<Integer> {
    @Id
    private Integer id = null;
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Column
    private LocalDate date;
    @Column
    private Boolean status;

    public Request(Book book, LocalDate date) {
        this.book = book;
        this.date = date;
        this.status = true;
    }

//    public Request(){
//
//    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

//    public Book getBook() {
//        return book;
//    }
//
//    public void setBook(Book book) {
//        this.book = book;
//    }
//
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public Boolean getStatus() {
//        return status;
//    }
//
//    public void setStatus(Boolean status) {
//        this.status = status;
//    }

    @Override
    public String toString(){
        return "Запрос #" + getId() + " [Книга " +
                book.getTitle() + " Автор " + book.getAuthor() +
                " Стоимость " + book.getCost() +
                " Дата создания " + date +  " Статус " + getStatus() + "]";
    }

//    @Override
//    public int compareTo(Request request) {
//        return this.id - request.id;
//    }

}
