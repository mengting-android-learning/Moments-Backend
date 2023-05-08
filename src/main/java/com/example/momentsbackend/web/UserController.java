package com.example.momentsbackend.web;

import com.example.momentsbackend.domain.User;
import com.example.momentsbackend.service.UserService;
import com.example.momentsbackend.web.dto.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public User saveUser(@RequestBody CreateUserRequest user) {
        System.out.println(System.currentTimeMillis());
        return service.saveUser(user);
    }

    @GetMapping
    public User findUserByName(@RequestParam(name = "name") String userName) {
        return service.findUserByName(userName);
    }
}
