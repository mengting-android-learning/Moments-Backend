package com.example.momentsbackend.service;

import com.example.momentsbackend.domain.User;
import com.example.momentsbackend.web.dto.request.CreateUserRequest;
import com.example.momentsbackend.entity.UserEntity;
import com.example.momentsbackend.mapper.UserMapper;
import com.example.momentsbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public User saveUser(CreateUserRequest userRequest) {
        UserEntity entity = userMapper.toEntity(userRequest);
        UserEntity optionalUserEntity = userRepository.findUserByName(entity.getUserName());
        if (optionalUserEntity != null) {
            return userMapper.toDomainUser(optionalUserEntity);
        } else {
            UserEntity savedUserEntity = userRepository.save(entity);
            return userMapper.toDomainUser(savedUserEntity);
        }
    }

    public User findUserByName(String userName) {
        UserEntity userEntity = userRepository.findUserByName(userName);
        return userMapper.toDomainUser(userEntity);
    }

}
