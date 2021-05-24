package com.senla.courses.dto;

import com.senla.courses.mappers.BookMapper;
import com.senla.courses.mappers.CustomerMapper;
import com.senla.courses.model.Book;
import com.senla.courses.model.Customer;
import lombok.Data;
import org.mapstruct.factory.Mappers;

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
        this.customer = Mappers.getMapper(CustomerMapper.class).customerToCustomerDto(customer);
    }

    public void setBooks(List<Book> books) {
        this.books = Mappers.getMapper(BookMapper.class).bookListToBookDtoList(books);
    }

    public OrderDto(Integer id, Customer customer, List<Book> books, LocalDate creationDate, LocalDate completionDate, Double totalCost, String status) {
        this.id = id;
        this.customer = Mappers.getMapper(CustomerMapper.class).customerToCustomerDto(customer);
        this.books = Mappers.getMapper(BookMapper.class).bookListToBookDtoList(books);
        this.creationDate = creationDate;
        this.completionDate = completionDate;
        this.totalCost = totalCost;
        this.status = status;
    }
}
