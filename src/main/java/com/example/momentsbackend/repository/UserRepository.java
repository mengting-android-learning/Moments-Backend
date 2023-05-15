package com.example.momentsbackend.repository;

import com.example.momentsbackend.domain.BaseUser;
import com.example.momentsbackend.domain.User;
import com.example.momentsbackend.web.dto.request.CreateUserRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    BaseUser findSenderById(Long id);

    User findUserByName(String name);

    User saveUser(CreateUserRequest request);
}
