package com.example.momentsbackend.service;

import com.example.momentsbackend.domain.Sender;
import com.example.momentsbackend.domain.TweetComment;
import com.example.momentsbackend.domain.TweetImage;
import com.example.momentsbackend.entity.TweetCommentEntity;
import com.example.momentsbackend.entity.TweetEntity;
import com.example.momentsbackend.entity.TweetImageEntity;
import com.example.momentsbackend.entity.UserEntity;
import com.example.momentsbackend.mapper.TweetMapper;
import com.example.momentsbackend.mapper.UserMapper;
import com.example.momentsbackend.repository.TweetCommentRepository;
import com.example.momentsbackend.repository.TweetImageRepository;
import com.example.momentsbackend.repository.TweetRepository;
import com.example.momentsbackend.repository.UserRepository;
import com.example.momentsbackend.web.dto.TweetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private final TweetMapper tweetMapper;

    public List<TweetResponse> findAll() {
        List<TweetResponse> tweets = new ArrayList<>();
        List<TweetEntity> tweetEntities = tweetRepository.findAll();
        for (TweetEntity tweetEntity : tweetEntities) {
            Sender sender = null;
            Optional<UserEntity> senderEntity = userRepository.findById(tweetEntity.getSenderId());
            if (senderEntity.isPresent()) {
                sender = userMapper.toDto(senderEntity.get());
            }
            List<TweetImageEntity> imageEntities = imageRepository.getImagesByTweetId(tweetEntity.getId());
            List<TweetImage> images = imageEntities
                    .stream()
                    .map(tweetMapper::toDomain)
                    .toList();
            List<TweetComment> comments = new ArrayList<>();
            List<TweetCommentEntity> commentEntities = commentRepository.getCommentsByTweetId(tweetEntity.getId());
            for (TweetCommentEntity commentEntity : commentEntities) {
                Sender commentSender = null;
                Optional<UserEntity> commentSenderEntity = userRepository.findById(commentEntity.getSenderId());
                TweetComment comment = tweetMapper.toDomain(commentEntity);
                if (commentSenderEntity.isPresent()) {
                    commentSender = userMapper.toDto(commentSenderEntity.get());
                }
                comment.setSender(commentSender);
                comments.add(comment);
            }
            TweetResponse tweet = new TweetResponse(
                    tweetEntity.getId(),
                    tweetEntity.getContent(),
                    tweetEntity.getCreatedOn(),
                    images,
                    sender,
                    comments
            );
            tweets.add(tweet);
        }
        return tweets;
    }
}
