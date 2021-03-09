package com.senla.courses.api.service;

import com.senla.courses.model.Customer;

import java.util.List;

public interface ICustomerService {

    void save(Customer customer);
    void saveAll();
    List<Customer> getAll();
}
