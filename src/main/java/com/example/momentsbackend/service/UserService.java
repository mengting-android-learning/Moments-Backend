package com.example.momentsbackend.service;

import com.example.momentsbackend.domain.User;
import com.example.momentsbackend.exception.UserNameExistsException;
import com.example.momentsbackend.repository.UserRepository;
import com.example.momentsbackend.web.dto.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(CreateUserRequest userRequest) throws Exception {
        if (userRepository.findUserByName(userRequest.getUserName()) != null) {
            throw new UserNameExistsException(userRequest.getUserName());
        }
        return userRepository.saveUser(userRequest);
    }

    public User findUserByName(String userName) {
        return userRepository.findUserByName(userName);
    }

}
