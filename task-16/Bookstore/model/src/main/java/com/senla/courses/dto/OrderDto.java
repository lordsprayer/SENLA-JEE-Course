package com.senla.courses.dto;

import com.senla.courses.mappers.CustomerMapper;
import com.senla.courses.model.Book;
import com.senla.courses.model.Customer;
import com.senla.courses.util.Converter;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDto {
    private Integer id;
    private CustomerDto customer;
    private List<BookDto> books;
    private LocalDate creationDate;
    private LocalDate completionDate;
    private Double totalCost;
    private String status;

    public void setCustomer(Customer customer) {
        this.customer = CustomerMapper.INSTANCE.customerToCustomerDto(customer);
    }

    public void setBooks(List<Book> books) {
        this.books = Converter.convertBooks(books);
    }

    public OrderDto(Integer id, Customer customer, List<Book> books, LocalDate creationDate, LocalDate completionDate, Double totalCost, String status) {
        this.id = id;
        this.customer = CustomerMapper.INSTANCE.customerToCustomerDto(customer);;
        this.books = Converter.convertBooks(books);;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
        this.totalCost = totalCost;
        this.status = status;
    }
}
