package com.senla.courses.service;

import com.senla.courses.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {

    void save(CustomerDto customer);
    void delete(Integer id);
    void update(CustomerDto customer);
    List<CustomerDto> getAll();
    CustomerDto getById(Integer id);
}
