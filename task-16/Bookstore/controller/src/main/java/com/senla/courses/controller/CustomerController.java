package com.senla.courses.controller;

import com.senla.courses.dto.BookDto;
import com.senla.courses.dto.CustomerDto;
import com.senla.courses.exception.DaoException;
import com.senla.courses.mappers.BookMapper;
import com.senla.courses.mappers.CustomerMapper;
import com.senla.courses.model.Book;
import com.senla.courses.model.Customer;
import com.senla.courses.service.IBookService;
import com.senla.courses.service.ICustomerService;
import com.senla.courses.service.IOrderService;
import com.senla.courses.service.IRequestService;
import com.senla.courses.util.Converter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private static final Logger log = LogManager.getLogger(CustomerController.class.getName());
    private final IBookService bookService;
    private final ICustomerService customerService;
    private final IRequestService requestService;
    private final IOrderService orderService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
            log.log(Level.INFO, "Received request: /customers");
            return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable Integer id){
        log.log(Level.INFO, "Received get request: /customers/" + id);
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerDto customerDto){
        log.log(Level.INFO, "Received post request: /customers");
        customerService.save(customerDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id){
        log.log(Level.INFO, "Received delete request: /customers");
        customerService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody CustomerDto customer){
        log.log(Level.INFO, "Received put request: /customers");
        customerService.update(customer);
        return ResponseEntity.ok().build();
    }
}
