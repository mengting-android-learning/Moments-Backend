package com.example.momentsbackend.service;

import com.example.momentsbackend.domain.Sender;
import com.example.momentsbackend.entity.TweetCommentEntity;
import com.example.momentsbackend.entity.TweetEntity;
import com.example.momentsbackend.entity.TweetImageEntity;
import com.example.momentsbackend.entity.UserEntity;
import com.example.momentsbackend.mapper.UserMapper;
import com.example.momentsbackend.repository.TweetCommentRepository;
import com.example.momentsbackend.repository.TweetImageRepository;
import com.example.momentsbackend.repository.TweetRepository;
import com.example.momentsbackend.repository.UserRepository;
import com.example.momentsbackend.web.dto.TweetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;
    private final TweetImageRepository imageRepository;
    private final TweetCommentRepository commentRepository;
    private final UserMapper userMapper;

    public List<TweetResponse> findAll() {
        List<TweetEntity> tweetEntities = tweetRepository.findAll();
        for (TweetEntity tweet : tweetEntities) {
            Optional<UserEntity> senderEntity = userRepository.findById(tweet.getSenderId());
            List<TweetImageEntity> imageEntities = imageRepository.getImagesByTweetId(tweet.getId());
            List<TweetCommentEntity> commentEntities = commentRepository.getCommentsByTweetId(tweet.getId());
            for(TweetCommentEntity commentEntity:commentEntities){
                Optional<UserEntity> commentSender = userRepository.findById(commentEntity.getSenderId());
            }
            if(senderEntity.isPresent()) {
                Sender sender = userMapper.toDto(senderEntity.get());
            }
        }
        return Collections.emptyList();
    }
}
