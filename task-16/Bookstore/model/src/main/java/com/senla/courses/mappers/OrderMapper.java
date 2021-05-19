package com.senla.courses.mappers;

import com.senla.courses.dto.OrderDto;
import com.senla.courses.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "bookList", target = "books")
    OrderDto orderToOrderDto(Order order);
}
