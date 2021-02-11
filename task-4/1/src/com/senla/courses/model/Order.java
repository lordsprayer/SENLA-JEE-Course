package com.senla.courses.model;


import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Order extends AId implements Comparable<Order>{

    private List<Book> bookList;
    private LocalDate creationDate;
    private LocalDate completionDate;
    private Double totalCost;
    private Status status;

    public enum Status
    {
        NEW(1),
        COMPLETED(2),
        CANCELED(3);

        private Integer severity;

        Status(Integer severity) {
            this.severity = severity;
        }
    }

    public Double calculateTotalCost(List<Book> bookList){
        double totalCost=0;
        for(Book book : bookList){
            //Double cost = book.getCost();
            totalCost += book.getCost();;
        }
        return totalCost;
    }

    public Order(List<Book> bookList, LocalDate creationDate) {
        this.bookList = bookList;
        this.creationDate = creationDate;
        this.completionDate = LocalDate.of(1970, 1, 1);
        this.totalCost = calculateTotalCost(bookList);
        this.status = Status.NEW;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
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
            str.append("\nBook { id = ").append(book.getId()).append(" name = ").append(book.getName()).append(" author = ").append(book.getAuthor()).append(" price = ").append(book.getCost()).append(" availability = ").append(book.getAvailability()).append("}");
        }
        str.append("\nTotal cost = ").append(totalCost).append(" status  = ").append(status);
        return str.toString();
    }

    @Override
    public int compareTo(Order order) {
        return (int)(this.id - order.id);
    }

    //компаратор по дате выполнения заказа
    public static Comparator<Order> DateComparator = Comparator.comparing(Order::getCompletionDate);

    //компаратор по стоимости заказа
    public static Comparator<Order> TotalCostComparator = Comparator.comparing(Order::getTotalCost);

    //компаратор по статусу заказа
    public static Comparator<Order> StatusComparator = Comparator.comparingInt(o -> o.getStatus().severity);
}
