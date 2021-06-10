package com.senla.courses.service;

import com.senla.courses.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {

    void createCustomer(CustomerDto customer);
    void deleteCustomer(Integer id);
    void updateCustomer(CustomerDto customer);
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(Integer id);
}
