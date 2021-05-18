package com.senla.courses.service;

import com.senla.courses.model.Customer;

import java.util.List;

public interface ICustomerService {

    void save(Customer customer);
    void delete(Customer customer);
    void update(Customer customer);
    List<Customer> getAll();
    Customer getById(Integer id);
}
