package com.senla.courses.controller;

import com.senla.courses.dto.RequestDto;
import com.senla.courses.service.IRequestService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {

    private static final Logger log = LogManager.getLogger(RequestController.class.getName());
    private final IRequestService requestService;

    @GetMapping
    public ResponseEntity<List<RequestDto>> getAllRequests(@RequestParam(defaultValue = "id") String sort) {
        log.log(Level.INFO, "Received get all request: /requests");
        if (sort.equals("book")) {
            return ResponseEntity.ok(requestService.getSortRequests());
        } else if (sort.equals("count")) {
            return ResponseEntity.ok(requestService.getSortRequestsByBookCount());
        } else {
            return ResponseEntity.ok(requestService.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> getById(@PathVariable Integer id){
        log.log(Level.INFO, "Received get request: /requests/" + id);
        return ResponseEntity.ok(requestService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createRequest(@RequestBody Integer id){
        log.log(Level.INFO, "Received post request: /requests");
        requestService.createRequest(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Integer id){
        log.log(Level.INFO, "Received delete request: /requests");
        requestService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    public ResponseEntity<Void> closeRequest(@RequestBody Integer id){
        log.log(Level.INFO, "Received put request: /requests");
        requestService.closeRequest(id);
        return ResponseEntity.ok().build();
    }


}
