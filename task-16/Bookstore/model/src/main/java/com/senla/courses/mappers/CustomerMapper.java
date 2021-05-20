package com.senla.courses.mappers;

import com.senla.courses.dto.CustomerDto;
import com.senla.courses.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    @Mapping(source = "phoneNumber", target = "phone")
    CustomerDto customerToCustomerDto(Customer customer);

    @Mapping(source = "phone", target = "phoneNumber")
    Customer customerDtoToCustomer(CustomerDto customer);
}
