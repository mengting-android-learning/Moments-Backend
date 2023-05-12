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
import com.example.momentsbackend.web.dto.request.CreateCommentRequest;
import com.example.momentsbackend.web.dto.request.CreateTweetImagesRequest;
import com.example.momentsbackend.web.dto.request.CreateTweetRequest;
import com.example.momentsbackend.web.dto.response.TweetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
    private final TweetMapper tweetMapper;

    public List<TweetResponse> findAll() {
        List<TweetEntity> tweetEntities = tweetRepository.findAllByOrderByCreatedOnDesc();
        return CollectionUtils.isEmpty(tweetEntities) ?
                Collections.emptyList() :
                tweetEntities.stream()
                        .map(this::getTweetResponse)
                        .toList();
    }

    public TweetResponse saveTweet(CreateTweetRequest tweetRequest) {
        TweetEntity tweetEntity = tweetRepository.save(new TweetEntity(null, tweetRequest.getContent(), tweetRequest.getCreatedOn(), tweetRequest.getUserId()));
        if (tweetRequest.getImages() != null) {
            for (CreateTweetImagesRequest image : tweetRequest.getImages()) {
                imageRepository.save(new TweetImageEntity(null, image.getUrl(), tweetEntity.getId()));
            }
        }
        return getTweetResponse(tweetEntity);
    }

    public TweetComment saveComment(CreateCommentRequest commentRequest) {
        TweetCommentEntity commentEntity = commentRepository.save(new TweetCommentEntity(null, commentRequest.getContent(),
                commentRequest.getCreatedOn(), commentRequest.getTweetId(), commentRequest.getSenderId()));
        TweetComment comment = tweetMapper.toDomain(commentEntity);
        comment.setSender(getSender(commentEntity.getSenderId()));
        return comment;
    }

    private TweetResponse getTweetResponse(TweetEntity tweetEntity) {
        Sender sender = getSender(tweetEntity.getSenderId());
        List<TweetImage> images = getImages(tweetEntity.getId());
        List<TweetComment> comments = getComments(tweetEntity.getId());
        return new TweetResponse(
                tweetEntity.getId(),
                tweetEntity.getContent(),
                tweetEntity.getCreatedOn(),
                images,
                sender,
                comments
        );
    }

    private List<TweetImage> getImages(Long id) {
        List<TweetImageEntity> imageEntities = imageRepository.getImagesByTweetId(id);
        return imageEntities.stream()
                .map(tweetMapper::toDomain)
                .toList();
    }

    private Sender getSender(Long senderId) {
        Optional<UserEntity> senderEntity = userRepository.findById(senderId);
        return senderEntity.map(userMapper::toDomainSender).orElse(null);
    }

    private List<TweetComment> getComments(Long id) {
        List<TweetComment> comments = new ArrayList<>();
        List<TweetCommentEntity> commentEntities = commentRepository.getCommentsByTweetId(id);
        commentEntities.forEach(
                commentEntity -> {
                    TweetComment comment = tweetMapper.toDomain(commentEntity);
                    comment.setSender(getSender(commentEntity.getSenderId()));
                    comments.add(comment);
                }
        );
        return comments;
    }

    public boolean deleteTweet(Long id) {
        commentRepository.deleteByTweetId(id);
        imageRepository.deleteByTweetId(id);
        tweetRepository.deleteById(id);
        return true;
    }
}
