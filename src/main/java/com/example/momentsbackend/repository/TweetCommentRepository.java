package com.example.momentsbackend.repository;

import com.example.momentsbackend.entity.TweetCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetCommentRepository extends JpaRepository<TweetCommentEntity, Long> {
    @Query(
            value = "SELECT * FROM tweet_comments WHERE tweet_id = id",
            nativeQuery = true
    )
    List<TweetCommentEntity> getCommentsByTweetId(Long id);
}
