package com.senla.courses.controller;

import com.senla.courses.model.User;
import com.senla.courses.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private static final Logger log = LogManager.getLogger(CustomerController.class.getName());
    private final IUserService userService;

    @GetMapping()
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<User>> userList() {
        log.log(Level.INFO, "Received get request: /customers");
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping()
    public String  deleteUser(@RequestParam(defaultValue = "") Integer userId,
                              @RequestParam(defaultValue = "") String action) {
        if (action.equals("delete")){
            userService.delete(userId);
        }
        return "redirect:/admin";
    }

//    @GetMapping("/gt/{userId}")
//    public String  gtUser(@PathVariable("userId") Long userId, Model model) {
//        model.addAttribute("allUsers", userService.usergtList(userId));
//        return "admin";
//    }
}
