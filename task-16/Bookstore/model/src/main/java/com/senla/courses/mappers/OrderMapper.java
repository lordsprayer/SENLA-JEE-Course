package com.senla.courses.mappers;

import com.senla.courses.dto.OrderDto;
import com.senla.courses.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "bookList", target = "books")
    OrderDto orderToOrderDto(Order order);

    @Mapping(source = "books", target = "bookList")
    Order orderDtoToOrder(OrderDto order);

    @Mapping(source = "bookList", target = "books")
    List<OrderDto> orderListToOrderDtoList(List<Order> orders);
}
