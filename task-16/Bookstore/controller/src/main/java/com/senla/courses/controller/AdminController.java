package com.senla.courses.controller;

import com.senla.courses.model.User;
import com.senla.courses.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class AdminController {

    private static final Logger log = LogManager.getLogger(CustomerController.class.getName());
    private final IUserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> userList() {
        log.log(Level.INFO, "Received get request: /users");
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void>  deleteUser(@PathVariable Integer id) {
        log.log(Level.INFO, "Received delete request: /users/" + id);
        userService.delete(id);
        return ResponseEntity.accepted().build();
    }
}
