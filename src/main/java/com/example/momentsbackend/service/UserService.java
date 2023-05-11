package com.example.momentsbackend.service;

import com.example.momentsbackend.domain.User;
import com.example.momentsbackend.exception.UserNameExistsException;
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

    public User saveUser(CreateUserRequest userRequest) throws UserNameExistsException {
        if(userRepository.findUserByName(userRequest.getUserName())!=null){
            throw new UserNameExistsException(userRequest.getUserName());
        }
        UserEntity entity = userMapper.toEntity(userRequest);
        UserEntity savedUserEntity = userRepository.save(entity);
        return userMapper.toDomainUser(savedUserEntity);
    }

    public User findUserByName(String userName) {
        UserEntity userEntity = userRepository.findUserByName(userName);
        return userMapper.toDomainUser(userEntity);
    }

}
