package com.example.momentsbackend.repository;

import com.example.momentsbackend.domain.BaseUser;
import com.example.momentsbackend.domain.User;
import com.example.momentsbackend.entity.UserEntity;
import com.example.momentsbackend.mapper.UserMapper;
import com.example.momentsbackend.repository.jpa.JpaUserRepository;
import com.example.momentsbackend.web.dto.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryProvider implements UserRepository {

    private final JpaUserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public BaseUser findSenderById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.map(userMapper::toDomainSender).orElse(null);
    }

    @Override
    public User findUserByName(String name) {
        Optional<UserEntity> userEntity = userRepository.findUserByName(name);
        return userEntity.map(userMapper::toDomainUser).orElse(null);
    }

    @Override
    public User saveUser(CreateUserRequest request) {
        UserEntity userEntity = userRepository.save(userMapper.toEntity(request));
        return userMapper.toDomainUser(userEntity);
    }
}
