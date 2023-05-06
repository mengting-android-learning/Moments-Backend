package com.example.momentsbackend.service;

import com.example.momentsbackend.entity.TweetEntity;
import com.example.momentsbackend.entity.TweetImageEntity;
import com.example.momentsbackend.repository.TweetImageRepository;
import com.example.momentsbackend.repository.TweetRepository;
import com.example.momentsbackend.web.dto.TweetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepository tweetRepository;
    private final TweetImageRepository imageRepository;
    public List<TweetResponse> findAll() {
        List<TweetEntity> tweetEntities = tweetRepository.findAll();
        for(TweetEntity tweet:tweetEntities){
            List<TweetImageEntity> imageEntities = imageRepository.getImagesByTweetId(tweet.getId());
        }
        return Collections.emptyList();
    }
}
