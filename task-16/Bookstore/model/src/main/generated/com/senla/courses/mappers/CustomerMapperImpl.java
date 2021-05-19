package com.senla.courses.mappers;

import com.senla.courses.dto.CustomerDto;
import com.senla.courses.model.Customer;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-19T12:26:33+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Amazon.com Inc.)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        String phone = null;
        Integer id = null;
        String name = null;
        String surname = null;

        phone = customer.getPhoneNumber();
        id = customer.getId();
        name = customer.getName();
        surname = customer.getSurname();

        CustomerDto customerDto = new CustomerDto( id, name, surname, phone );

        return customerDto;
    }
}
