package com.senla.courses.model;

import java.util.Date;

public class Book extends AId {
    private String name;
    private String author;
    //private Date publicationDate;
    private Double cost;
    //private Date receiptDate;
    private Boolean availability;

    public Book(String name,
                String author,
                //Date publicationDate,
                Double cost,
                //Date dateOfReceipt,
                Boolean availability) {
        this.name = name;
        this.author = author;
        //this.publicationDate = publicationDate;
        this.cost = cost;
        //this.receiptDate = dateOfReceipt;
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
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
                name + " author = " + author + " price = " + cost + " availability = " +availability + "}";
    }


}
