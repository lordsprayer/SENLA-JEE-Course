package com.senla.courses.dao;

import com.senla.courses.api.dao.ICustomerDao;
import com.senla.courses.di.api.annotation.Singleton;
import com.senla.courses.exception.DaoException;
import com.senla.courses.model.Customer;
import com.senla.courses.util.SerializationHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class CustomerDao implements ICustomerDao {

    private static final String GET_BY_ID_ERROR_MESSAGE = "Could not find an customer by id: %d";
    private static final Logger log = Logger.getLogger(CustomerDao.class.getName());
    private List<Customer> customers;

    public CustomerDao() {
        try{
            customers = SerializationHandler.deserialize(Customer.class);
        } catch(Exception e){
            customers = new ArrayList<>();}
    }

    @Override
    public void save(Customer customer) {
        try {
            customer.setId(customers.get(customers.size() - 1).getId() + 1L);
        } catch (Exception e){
            customer.setId(1L);
        }
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
        log.log(Level.WARNING, String.format(GET_BY_ID_ERROR_MESSAGE, id));
        throw new DaoException(String.format(GET_BY_ID_ERROR_MESSAGE, id));
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(customers);
    }

    @Override
    public void saveAll() {
        SerializationHandler.serialize(customers);
    }
}
