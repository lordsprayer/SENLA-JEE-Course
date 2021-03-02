package com.senla.courses.dao;

import com.senla.courses.api.dao.ICustomerDao;
import com.senla.courses.model.Customer;
import com.senla.courses.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements ICustomerDao {

    private final List<Customer> customers = new ArrayList<>();

    @Override
    public void save(Customer customer) {
    customer.setId(IdGenerator.GenerateCustomerId());
    customers.add(customer);
    }

    @Override
    public void delete(Customer customer) {
        customers.remove(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Customer customer1 = getById(customer.getId());
        customer1.setName(customer.getName());
        customer1.setSurname(customer.getSurname());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        return customer1;
    }

    @Override
    public Customer getById(Long id) {
        for(Customer customer : customers){
            if (id.equals(customer.getId())){
                return customer;
            }
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(customers);
    }
}
