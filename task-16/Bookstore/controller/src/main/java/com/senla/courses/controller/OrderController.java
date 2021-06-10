package com.senla.courses.controller;

import com.senla.courses.dto.OrderDto;
import com.senla.courses.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private static final Logger log = LogManager.getLogger(OrderController.class.getName());
    private final IOrderService orderService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<OrderDto>> getAllOrders(@RequestParam(defaultValue = "id") String sort,
                                                       @RequestParam(defaultValue = "1970-01-01") String date){
        log.log(Level.INFO, "Received get request: /orders?sort=" + sort + "&date=" + date);
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(orderService.getAllOrders(localDate, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable Integer id){
        log.log(Level.INFO, "Received get request: /orders/" + id);
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody List<Integer> booksId,
                                            @RequestParam Integer customerId){
        log.log(Level.INFO, "Received post request: /orders/customerId=" + customerId);
        orderService.createOrder(customerId, booksId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id){
        log.log(Level.INFO, "Received delete request: /orders/" + id);
        orderService.deleteOrder(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    public ResponseEntity<Void> changeOrderStatus(@RequestBody String status,
                                                  @RequestParam Integer id){
        log.log(Level.INFO, "Received put request: /orders?id=" + id);
        orderService.changeStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/income")
    public ResponseEntity<Double> getCountIncome(@RequestParam String date){
        log.log(Level.INFO, "Received get request: /orders/income?date=" + date);
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(orderService.countIncome(localDate));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCountCompleteOrders(@RequestParam String date){
        log.log(Level.INFO, "Received get request: /orders/count?date=" + date);
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(orderService.countCompleteOrders(localDate));
    }


}