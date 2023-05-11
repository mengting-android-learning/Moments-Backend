package com.example.momentsbackend.repository;

import com.example.momentsbackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);

    UserEntity save(UserEntity userEntity);

    @Query(
            value = "SELECT * FROM users WHERE user_name = ?1",
            nativeQuery = true
    )
    Optional<UserEntity> findUserByName(String userName);

    boolean existsById(Long id);
}
