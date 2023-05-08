package com.example.momentsbackend.repository;

import com.example.momentsbackend.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<TweetEntity, Long> {
    List<TweetEntity> findAll();

    TweetEntity save(TweetEntity tweetEntity);
}
