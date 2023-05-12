package com.example.momentsbackend.repository.jpa;

import com.example.momentsbackend.entity.TweetCommentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaTweetCommentRepository extends JpaRepository<TweetCommentEntity, Long> {
    @Query(
            value = "SELECT * FROM tweet_comments WHERE tweet_id = ?1",
            nativeQuery = true
    )
    List<TweetCommentEntity> getCommentsByTweetId(Long id);

    @Transactional
    void deleteByTweetId(Long id);
}
