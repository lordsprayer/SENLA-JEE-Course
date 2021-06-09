package com.senla.courses.model;

import com.senla.courses.util.Calculator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Identified<Integer>  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "order")//, cascade = CascadeType.ALL)
    private List<Book> bookList;
    @Column (name = "creation_date")
    private LocalDate creationDate;
    @Column (name = "completion_date")
    private LocalDate completionDate;
    @Column (name = "total_cost")
    private Double totalCost;
    @Column (name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status
    {
        NEW(1),
        COMPLETED(2),
        CANCELED(3);

        private final int severity;

        Status(int severity) {
            this.severity = severity;
        }

        public int getSeverity() {
            return severity;
        }

    }

    public Order(Customer customer, List<Book> bookList, LocalDate creationDate) {
        this.customer = customer;
        this.bookList = bookList;
        this.creationDate = creationDate;
        this.completionDate = LocalDate.of(1970, 1, 1);
        this.totalCost = Calculator.calculateTotalCost(bookList);
        this.status = Status.NEW;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) && Objects.equals(getCustomer(), order.getCustomer()) && Objects.equals(getBookList(), order.getBookList()) && Objects.equals(getCreationDate(), order.getCreationDate()) && Objects.equals(getCompletionDate(), order.getCompletionDate()) && Objects.equals(getTotalCost(), order.getTotalCost()) && getStatus() == order.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getBookList(), getCreationDate(), getCompletionDate(), getTotalCost(), getStatus());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Заказ #" + getId() + " \nПокупатель \n[" + getCustomer().getName()
                + " " + getCustomer().getSurname() + ", номер телефона " + getCustomer().getPhoneNumber()
                + "]\nКниги в заказе ");
        for (Book book : bookList) {
            str.append("\n[").append(book.getTitle()).append(" ").append(book.getAuthor())
                    .append(" стоимость ").append(book.getCost()).append("]");
        }
        str.append("\nОбщая стоимость заказа ").append(totalCost).append(" статус = ").append(status);
        return str.toString();
    }
}
