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
@Table (name = "requests")
public class Request implements Identified<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
    @Column (name = "date")
    private LocalDate date;
    @Column (name = "status")
    private Boolean status;

    public Request(Book book, LocalDate date) {
        this.book = book;
        this.date = date;
        this.status = true;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString(){
        return "Запрос #" + getId() + " [Книга " +
                book.getTitle() + " Автор " + book.getAuthor() +
                " Стоимость " + book.getCost() +
                " Дата создания " + date +  " Статус " + getStatus() + "]";
    }
}
