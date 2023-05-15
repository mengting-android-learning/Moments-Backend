package com.example.momentsbackend.mapper;

import com.example.momentsbackend.domain.BaseUser;
import com.example.momentsbackend.domain.User;
import com.example.momentsbackend.web.dto.request.CreateUserRequest;
import com.example.momentsbackend.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public BaseUser toDomainSender(UserEntity userEntity) {
        return mapper.map(userEntity, BaseUser.class);
    }

    public User toDomainUser(UserEntity userEntity) {
        return mapper.map(userEntity, User.class);
    }

    public UserEntity toEntity(CreateUserRequest userRequest){
        return mapper.map(userRequest, UserEntity.class);
    }
}
