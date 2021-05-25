package com.senla.courses.mappers;

import com.senla.courses.dto.CustomerDto;
import com.senla.courses.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "phoneNumber", target = "phone")
    CustomerDto customerToCustomerDto(Customer customer);

    @Mapping(source = "phone", target = "phoneNumber")
    Customer customerDtoToCustomer(CustomerDto customer);

    @Mapping(source = "phoneNumber", target = "phone")
    List<CustomerDto> customerListToCustomerDtoList(List<Customer> customers);
}
