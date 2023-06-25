package com.example.CodeGenerations.controller;

import com.example.CodeGenerations.model.User;
import com.example.CodeGenerations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IndexController {
    private final UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        List<User> userList = userRepository.findAll();
        return "Total users: " + userList.size() + ". Users in database: " + userList;
    }
}
