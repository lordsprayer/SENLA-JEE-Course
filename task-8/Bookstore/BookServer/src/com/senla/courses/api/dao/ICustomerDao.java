package com.senla.courses.api.dao;

import com.senla.courses.model.Customer;

import java.util.List;

public interface ICustomerDao {
    void save(Customer customer);
    void delete(Customer customer);
    Customer update(Customer customer);
    Customer getById(Long id);
    List<Customer> getAll();
}
