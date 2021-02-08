package com.senla.courses.model;


import java.util.Date;
import java.util.List;

public class Order extends AId {

    private List<Book> bookList;
    //private Date creationDate;
    //private Date completionDate;
    private Double totalCost;
    private Status status;

    public enum Status
    {
        NEW,
        COMPLETED,
        CANCELED
    }

    public Double calculateTotalCost(List<Book> bookList){
        double totalCost=0;
        for(Book book : bookList){
            //Double cost = book.getCost();
            totalCost += book.getCost();;
        }
        return totalCost;
    }

    public Order(List<Book> bookList) {
        this.bookList = bookList;
        //this.creationDate = creationDate;
        //this.completionDate = completionDate;
        this.totalCost = calculateTotalCost(bookList);
        this.status = Status.NEW;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Order number " + getId());
        for (Book book : bookList) {
            str.append("\nBook { id = " + book.getId() + " name = " +
                    book.getName() + " author = " + book.getAuthor() +
                    " price = " + book.getCost() + " availability = " + book.getAvailability() + "}");
        }
        str.append("\nTotal cost = " + totalCost + " status  = " + status);
        String str1 = str.toString();
        return str1;
    }
}
