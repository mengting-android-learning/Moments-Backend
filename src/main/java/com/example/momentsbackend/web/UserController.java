package com.example.momentsbackend.web;

import com.example.momentsbackend.domain.User;
import com.example.momentsbackend.dto.CreateUserRequest;
import com.example.momentsbackend.service.TweetService;
import com.example.momentsbackend.service.UserService;
import com.example.momentsbackend.web.dto.TweetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    @PostMapping
    public User saveUser(@RequestBody CreateUserRequest user) {
        return service.saveUser(user);
    }
}
