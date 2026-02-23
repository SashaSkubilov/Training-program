package com.example.training_program.controller;

import com.example.training_program.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-with-plan")
    public String testTransaction(@RequestParam String name, @RequestParam String plan) {
        userService.createUserAndPlan(name, plan);
        return "Успешно!";
    }
}