package com.example.momentsbackend.web;

import com.example.momentsbackend.domain.User;
import com.example.momentsbackend.service.UserService;
import com.example.momentsbackend.web.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public User saveUser(@RequestBody CreateUserRequest user) {
        return service.saveUser(user);
    }

    @GetMapping
    public User findUserByName(@RequestParam(name = "name") String userName) {
        return service.findUserByName(userName);
    }
}
