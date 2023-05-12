package com.example.momentsbackend.repository;

import com.example.momentsbackend.entity.TweetImageEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetImageRepository extends JpaRepository<TweetImageEntity, Long> {

    @Query(
            value = "SELECT * FROM tweet_images WHERE tweet_id = ?1",
            nativeQuery = true
    )
    List<TweetImageEntity> getImagesByTweetId(Long id);

    TweetImageEntity save(TweetImageEntity image);

    @Transactional
    List<TweetImageEntity> deleteByTweetId(Long id);
}
