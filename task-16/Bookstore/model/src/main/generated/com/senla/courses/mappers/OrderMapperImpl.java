package com.senla.courses.mappers;

import com.senla.courses.dto.BookDto;
import com.senla.courses.dto.CustomerDto;
import com.senla.courses.dto.OrderDto;
import com.senla.courses.model.Book;
import com.senla.courses.model.Customer;
import com.senla.courses.model.Order;
import com.senla.courses.model.Order.Status;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-24T22:26:42+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Amazon.com Inc.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto orderToOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        List<Book> books = null;
        Customer customer = null;
        Integer id = null;
        LocalDate creationDate = null;
        LocalDate completionDate = null;
        Double totalCost = null;
        String status = null;

        List<Book> list = order.getBookList();
        if ( list != null ) {
            books = new ArrayList<Book>( list );
        }
        customer = order.getCustomer();
        id = order.getId();
        creationDate = order.getCreationDate();
        completionDate = order.getCompletionDate();
        totalCost = order.getTotalCost();
        if ( order.getStatus() != null ) {
            status = order.getStatus().name();
        }

        OrderDto orderDto = new OrderDto( id, customer, books, creationDate, completionDate, totalCost, status );

        return orderDto;
    }

    @Override
    public Order orderDtoToOrder(OrderDto order) {
        if ( order == null ) {
            return null;
        }

        Order order1 = new Order();

        order1.setBookList( bookDtoListToBookList( order.getBooks() ) );
        if ( order.getId() != null ) {
            order1.setId( order.getId() );
        }
        order1.setCustomer( customerDtoToCustomer( order.getCustomer() ) );
        order1.setCreationDate( order.getCreationDate() );
        order1.setCompletionDate( order.getCompletionDate() );
        order1.setTotalCost( order.getTotalCost() );
        if ( order.getStatus() != null ) {
            order1.setStatus( Enum.valueOf( Status.class, order.getStatus() ) );
        }

        return order1;
    }

    @Override
    public List<OrderDto> orderListToOrderDtoList(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderDto> list = new ArrayList<OrderDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( orderToOrderDto( order ) );
        }

        return list;
    }

    protected Book bookDtoToBook(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        if ( bookDto.getId() != null ) {
            book.setId( bookDto.getId() );
        }
        book.setTitle( bookDto.getTitle() );
        book.setAuthor( bookDto.getAuthor() );
        book.setPublicationYear( bookDto.getPublicationYear() );
        book.setCost( bookDto.getCost() );
        book.setReceiptDate( bookDto.getReceiptDate() );
        book.setAvailability( bookDto.getAvailability() );
        book.setDescription( bookDto.getDescription() );

        return book;
    }

    protected List<Book> bookDtoListToBookList(List<BookDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Book> list1 = new ArrayList<Book>( list.size() );
        for ( BookDto bookDto : list ) {
            list1.add( bookDtoToBook( bookDto ) );
        }

        return list1;
    }

    protected Customer customerDtoToCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        if ( customerDto.getId() != null ) {
            customer.setId( customerDto.getId() );
        }
        customer.setName( customerDto.getName() );
        customer.setSurname( customerDto.getSurname() );

        return customer;
    }
}
