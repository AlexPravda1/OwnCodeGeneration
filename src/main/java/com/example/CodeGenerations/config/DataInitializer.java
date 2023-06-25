package com.example.CodeGenerations.config;

import com.example.CodeGenerations.model.User;
import com.example.CodeGenerations.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final UserRepository userRepository;

    @PostConstruct
    public void insertUsers() {
        if (userRepository.findAll().size() == 0) {
            List<User> userList = new ArrayList<>();
            for (int i = 0; i < new Random().nextInt(25); i++) {
                userList.add(createUser());
            }
            userRepository.saveAll(userList);
        }
    }

    private User createUser() {
        int randomNumber = new Random().nextInt(99999);
        return User.builder()
                .name("User " + randomNumber)
                .surname("Surname " + randomNumber)
                .email(randomNumber + "@gmail.com")
                .build();
    }
}
