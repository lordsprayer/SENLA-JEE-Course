package com.senla.courses.util;

import com.senla.courses.dto.BookDto;
import com.senla.courses.dto.CustomerDto;
import com.senla.courses.dto.OrderDto;
import com.senla.courses.dto.RequestDto;
import com.senla.courses.mappers.BookMapper;
import com.senla.courses.mappers.CustomerMapper;
import com.senla.courses.mappers.OrderMapper;
import com.senla.courses.mappers.RequestMapper;
import com.senla.courses.model.Book;
import com.senla.courses.model.Customer;
import com.senla.courses.model.Order;
import com.senla.courses.model.Request;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static List<BookDto> convertBooks(List<Book> books) {
        List<BookDto> bookDtoList = new ArrayList<>();
        for(Book book : books) {
            BookDto bookDto = BookMapper.INSTANCE.bookToBookDto(book);
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    public static List<Book> convertBooksDto(List<BookDto> books) {
        List<Book> bookList = new ArrayList<>();
        for(BookDto bookDto : books) {
            Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
            bookList.add(book);
        }
        return bookList;
    }

    public static List<CustomerDto> convertCustomers(List<Customer> customers) {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for(Customer customer : customers) {
            CustomerDto customerDto = CustomerMapper.INSTANCE.customerToCustomerDto(customer);
            customerDtoList.add(customerDto);
        }
        return customerDtoList;
    }

    public static List<RequestDto> convertRequests(List<Request> requests) {
        List<RequestDto> requestDtoList = new ArrayList<>();
        for(Request request : requests) {
            RequestDto requestDto = RequestMapper.INSTANCE.requestToRequestDto(request);
            requestDtoList.add(requestDto);
        }
        return requestDtoList;
    }

    public static List<OrderDto> convertOrders(List<Order> orders) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for(Order order : orders) {
            OrderDto orderDto = OrderMapper.INSTANCE.orderToOrderDto(order);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}
