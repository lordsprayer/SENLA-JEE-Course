package com.senla.courses.controller;

import com.senla.courses.dto.CustomerDto;
import com.senla.courses.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private static final Logger log = LogManager.getLogger(CustomerController.class.getName());
    private final ICustomerService customerService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        log.log(Level.INFO, "Received get request: /customers");
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable Integer id){
        log.log(Level.INFO, "Received get request: /customers/" + id);
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerDto customerDto){
        log.log(Level.INFO, "Received post request: /customers");
        customerService.createCustomer(customerDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id){
        log.log(Level.INFO, "Received delete request: /customers");
        customerService.deleteCustomer(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody CustomerDto customer){
        log.log(Level.INFO, "Received put request: /customers");
        customerService.updateCustomer(customer);
        return ResponseEntity.noContent().build();
    }
}
