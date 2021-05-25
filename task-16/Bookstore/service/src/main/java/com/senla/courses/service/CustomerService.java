package com.senla.courses.service;

import com.senla.courses.dao.ICustomerDao;
import com.senla.courses.dto.CustomerDto;
import com.senla.courses.exception.DaoException;
import com.senla.courses.mappers.CustomerMapper;
import com.senla.courses.model.Customer;
import com.senla.courses.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService extends ConstantUtil implements ICustomerService {

    private static final Logger log = LogManager.getLogger (CustomerService.class.getName ());
    private final ICustomerDao customerDao;

    private final CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);

    @Override
    public void save(CustomerDto customerDto) {
        try {
            Customer customer = mapper.customerDtoToCustomer(customerDto);
            customerDao.persist(customer);
        } catch (DaoException e) {
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            Customer customer = customerDao.getByPK(id);
            customerDao.delete(customer);
        } catch (DaoException e) {
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void update(CustomerDto customerDto) {
        try {
            Customer customer = customerDao.getByPK(customerDto.getId());
            if (customer == null) {
                log.log(Level.WARN, SEARCH_ERROR);
            } else {
                customer.setName(customerDto.getName());
                customer.setSurname(customerDto.getSurname());
                customer.setPhoneNumber(customerDto.getPhone());
                customerDao.update(customer);
            }
        } catch (DaoException e) {
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }

    @Override
    public List<CustomerDto> getAll() {
        try {
            List<Customer> customers = customerDao.getAll();
            return mapper.customerListToCustomerDtoList(customers);
        } catch (DaoException e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public CustomerDto getById(Integer id) {
        try{
            Customer customer = customerDao.getByPK(id);
            return mapper.customerToCustomerDto(customer);
        } catch (DaoException e){
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }
}
