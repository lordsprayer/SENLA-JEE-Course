package com.senla.courses.mappers;

import com.senla.courses.dto.CustomerDto;
import com.senla.courses.model.Customer;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-21T14:13:36+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Amazon.com Inc.)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setPhone( customer.getPhoneNumber() );
        customerDto.setId( customer.getId() );
        customerDto.setName( customer.getName() );
        customerDto.setSurname( customer.getSurname() );

        return customerDto;
    }

    @Override
    public Customer customerDtoToCustomer(CustomerDto customer) {
        if ( customer == null ) {
            return null;
        }

        Customer customer1 = new Customer();

        customer1.setPhoneNumber( customer.getPhone() );
        if ( customer.getId() != null ) {
            customer1.setId( customer.getId() );
        }
        customer1.setName( customer.getName() );
        customer1.setSurname( customer.getSurname() );

        return customer1;
    }
}
