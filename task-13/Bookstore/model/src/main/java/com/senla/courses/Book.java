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
@Table (name = "books")
public class Book implements Identified<Integer>{
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private Integer id;
    @Column (name = "title")
    private String title;
    @Column (name = "author")
    private String author;
    @Column (name = "publication_year")
    private Integer publicationYear;
    @Column (name = "cost")
    private Double cost;
    @Column (name = "receipt_date")
    private LocalDate receiptDate;
    @Column (name = "availability")
    private Boolean availability;
    @Column (name = "description")
    private String description;
    @OneToOne(mappedBy = "book")
    private Request request;
    @ManyToOne (fetch = FetchType.LAZY)
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
