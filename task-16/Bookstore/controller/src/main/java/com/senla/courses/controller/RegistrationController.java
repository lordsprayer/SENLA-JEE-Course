package com.senla.courses.controller;

import com.senla.courses.model.User;
import com.senla.courses.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private static final Logger log = LogManager.getLogger(RegistrationController.class.getName());


    private final IUserService userService;

    @PostMapping()
    public ResponseEntity<String> addUser(@RequestBody User user) {
        log.log(Level.INFO, "Received post request: /registration");
        if (!user.getPassword().equals(user.getPasswordConfirm())){
            return ResponseEntity.ok("Passwords don't match");
        }
        if (!userService.save(user)){
            return ResponseEntity.ok("A user with this name already exists");
        }

        return ResponseEntity.ok("The user is registered successfully");
    }
}
