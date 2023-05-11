package com.example.momentsbackend.service;

import com.example.momentsbackend.domain.User;
import com.example.momentsbackend.exception.UserNameExistsException;
import com.example.momentsbackend.web.dto.request.CreateUserRequest;
import com.example.momentsbackend.entity.UserEntity;
import com.example.momentsbackend.mapper.UserMapper;
import com.example.momentsbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public User saveUser(CreateUserRequest userRequest) throws Exception {
        if (userRepository.findUserByName(userRequest.getUserName()).isPresent()) {
            throw new UserNameExistsException(userRequest.getUserName());
        }
        UserEntity entity = userMapper.toEntity(userRequest);
        UserEntity savedUserEntity = userRepository.save(entity);
        return userMapper.toDomainUser(savedUserEntity);
    }

    public User findUserByName(String userName) {
        Optional<UserEntity> userEntity = userRepository.findUserByName(userName);
        return userEntity.map(userMapper::toDomainUser).orElse(null);
    }

}
