package com.example.momentsbackend.web;

import com.example.momentsbackend.domain.User;
import com.example.momentsbackend.exception.UserDoesNotExistException;
import com.example.momentsbackend.exception.UserNameExistsException;
import com.example.momentsbackend.service.UserService;
import com.example.momentsbackend.web.dto.request.CreateUserRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService service;

    @PostMapping
    public User saveUser(@Valid @RequestBody CreateUserRequest user) throws Exception {
        return service.saveUser(user);
    }

    @GetMapping
    public User findUserByName(@Valid @RequestParam(name = "name") @NotBlank String userName) throws Exception {
        User user = service.findUserByName(userName);
        if (user == null)
            throw new UserDoesNotExistException("name:" + userName);
        return user;
    }
}
