package com.example.momentsbackend.repository;

import com.example.momentsbackend.domain.TweetComment;
import com.example.momentsbackend.domain.TweetImage;
import com.example.momentsbackend.entity.TweetCommentEntity;
import com.example.momentsbackend.entity.TweetEntity;
import com.example.momentsbackend.entity.TweetImageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository {
    List<TweetEntity> findAll();

    TweetEntity save(TweetEntity tweetEntity);

    void deleteById(Long id);

    List<TweetImage> getImagesByTweetId(Long id);

    TweetImage saveImage(TweetImageEntity image);

    void deleteImagesByTweetId(Long id);

    List<TweetComment> getCommentsByTweetId(Long id);

    void deleteCommentsByTweetId(Long id);

    TweetComment saveComment(TweetCommentEntity tweetCommentEntity);
}
