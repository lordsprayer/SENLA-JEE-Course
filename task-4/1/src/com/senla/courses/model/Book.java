package com.senla.courses.model;

import java.time.LocalDate;
import java.util.Comparator;

public class Book extends AId implements Comparable<Book>{
    private String name;
    private String author;
    private Integer publicationYear;
    private Double cost;
    private LocalDate receiptDate;
    private Boolean availability;

    public Book(String name,
                String author,
                Integer publicationYear,
                Double cost,
                LocalDate receiptDate,
                Boolean availability) {
        this.name = name;
        this.author = author;
        this.publicationYear = publicationYear;
        this.cost = cost;
        this.receiptDate = receiptDate;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString(){
        return "Book { id = " + getId() + " name = " +
                name + " author = " + author + " publication Year = " +
                publicationYear +" price = " + cost + " date Of Receipt = " +
                receiptDate +" availability = " +availability + "}";
    }


    @Override
    public int compareTo(Book book) {
        return (int)(this.id - book.id);
    }

    public static Comparator<Book> NameComparator = Comparator.comparing(Book::getName);

    public static Comparator<Book> CostComparator = Comparator.comparing(Book::getCost);;

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
