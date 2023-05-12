package com.example.momentsbackend.repository;

import com.example.momentsbackend.domain.Sender;
import com.example.momentsbackend.domain.User;
import com.example.momentsbackend.web.dto.request.CreateUserRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    
    public Sender findSenderById(Long id);
    
    public User findUserByName(String name);
    
    public User saveUser(CreateUserRequest request);
}
