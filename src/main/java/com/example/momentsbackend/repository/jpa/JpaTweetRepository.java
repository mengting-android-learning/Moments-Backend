package com.example.momentsbackend.repository.jpa;

import com.example.momentsbackend.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaTweetRepository extends JpaRepository<TweetEntity, Long> {
    List<TweetEntity> findAllByOrderByCreatedOnDesc();

    TweetEntity save(TweetEntity tweetEntity);

    void deleteById(Long id);
}
