package com.senla.courses.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

public class Book extends AId implements Comparable<Book>, Serializable {
    private String title;
    private String author;
    private Integer publicationYear;
    private Double cost;
    private LocalDate receiptDate;
    private Boolean availability;
    private String description;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Double getCost() { return cost; }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return "Книга [id " + getId() + " Название " +
                title + " Автор " + author + " Год публикации " +
                publicationYear +" Стоимость " + cost + " Дата поступления " +
                receiptDate +" Доступность  " + availability + "]";
    }


    @Override
    public int compareTo(Book book) {
        return (int)(this.id - book.id);
    }

    public static Comparator<Book> NameComparator = Comparator.comparing(Book::getTitle);

    public static Comparator<Book> CostComparator = Comparator.comparing(Book::getCost);

    public static Comparator<Book> AvailabilityComparator = (b1, b2) -> {
        if (b1.getAvailability() == b2.getAvailability()) {
            return 0;
        }
        if (b1.getAvailability() && !b2.getAvailability()) {
            return -1;
        }
        else {
            return 1;
        }
    };

    public static Comparator<Book> PublicationComparator = Comparator.comparing(Book::getPublicationYear);

    public static Comparator<Book> ReceiptComparator = Comparator.comparing(Book::getReceiptDate);
}
