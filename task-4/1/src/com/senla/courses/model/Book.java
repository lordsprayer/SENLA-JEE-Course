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

    public static Comparator<Book> NameComparator = new Comparator<Book>() {

        @Override
        public int compare(Book e1, Book e2) {
            return e1.getName().compareTo(e2.getName());
        }
    };

    public static Comparator<Book> CostComparator = new Comparator<Book>() {

        @Override
        public int compare(Book e1, Book e2) {
            return (int)(e1.getCost() - e2.getCost());
        }
    };

    public static Comparator<Book> AvailabilityComparator = new Comparator<Book>() {

        @Override
        public int compare(Book e1, Book e2) {
            return 1;//(int)(e1.getAvailability() - e2.getAvailability());
        }
    };

    public static Comparator<Book> PublicationComparator = new Comparator<Book>() {

        @Override
        public int compare(Book e1, Book e2) {
            return e1.getPublicationYear().compareTo(e2.getPublicationYear());
        }
    };
}
