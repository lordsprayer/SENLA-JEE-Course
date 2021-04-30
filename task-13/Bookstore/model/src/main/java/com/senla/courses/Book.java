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
public class Book implements Identified<Integer>{
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private Integer publicationYear;
    @Column
    private Double cost;
    @Column
    private LocalDate receiptDate;
    @Column
    private Boolean availability;
    @Column
    private String description;
    @OneToOne(mappedBy = "book")
    private Request request;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Book(String title,
                String author,
                Integer publicationYear,
                Double cost,
                LocalDate receiptDate,
                Boolean availability) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.cost = cost;
        this.receiptDate = receiptDate;
        this.availability = availability;
        this.description = null;
    }

//    public Book(){
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

    @Override
    public String toString() {
        return "Книга [id " + getId() + " Название " +
                title + " Автор " + author + " Год публикации " +
                publicationYear +" Стоимость " + cost + " Дата поступления " +
                receiptDate +" Доступность  " + availability + "]";
    }
//
//    @Override
//    public int compareTo(Book book) {
//        return this.id - book.id;
//    }
}
