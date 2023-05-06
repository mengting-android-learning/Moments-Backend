package com.example.momentsbackend.mapper;

import com.example.momentsbackend.domain.Sender;
import com.example.momentsbackend.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public Sender toDto(UserEntity userEntity) {
        return mapper.map(userEntity, Sender.class);
    }
}
