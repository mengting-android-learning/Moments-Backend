package com.example.momentsbackend.service;

import com.example.momentsbackend.domain.BaseUser;
import com.example.momentsbackend.domain.TweetComment;
import com.example.momentsbackend.domain.TweetImage;
import com.example.momentsbackend.entity.TweetCommentEntity;
import com.example.momentsbackend.entity.TweetEntity;
import com.example.momentsbackend.entity.TweetImageEntity;
import com.example.momentsbackend.repository.TweetRepository;
import com.example.momentsbackend.repository.UserRepository;
import com.example.momentsbackend.web.dto.request.CreateCommentRequest;
import com.example.momentsbackend.web.dto.request.CreateTweetImagesRequest;
import com.example.momentsbackend.web.dto.request.CreateTweetRequest;
import com.example.momentsbackend.web.dto.response.TweetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetService {

    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    public List<TweetResponse> findAll() {
        List<TweetEntity> tweetEntities = tweetRepository.findAll();
        return CollectionUtils.isEmpty(tweetEntities) ?
                Collections.emptyList() :
                tweetEntities.stream()
                        .map(this::getTweetResponse)
                        .toList();
    }

    public TweetResponse saveTweet(CreateTweetRequest tweetRequest) {
        TweetEntity tweetEntity = tweetRepository.save(new TweetEntity(null, tweetRequest.getContent(), tweetRequest.getCreatedOn(), tweetRequest.getUserId()));
        if (tweetRequest.getImages() != null && tweetRequest.getImages().size() != 0) {
            for (CreateTweetImagesRequest image : tweetRequest.getImages()) {
                tweetRepository.saveImage(new TweetImageEntity(null, image.getUrl(), tweetEntity.getId()));
            }
        }
        return getTweetResponse(tweetEntity);
    }

    public TweetComment saveComment(CreateCommentRequest commentRequest) {
        TweetComment comment = tweetRepository.saveComment(
                new TweetCommentEntity(null, commentRequest.getContent(),
                        commentRequest.getCreatedOn(), commentRequest.getTweetId(), commentRequest.getSenderId()));
        comment.setSender(userRepository.findSenderById(comment.getSender().getId()));
        return comment;
    }

    public boolean deleteTweet(Long id) {
        tweetRepository.deleteCommentsByTweetId(id);
        tweetRepository.deleteImagesByTweetId(id);
        tweetRepository.deleteById(id);
        return true;
    }

    private TweetResponse getTweetResponse(TweetEntity tweetEntity) {
        BaseUser sender = userRepository.findSenderById(tweetEntity.getId());
        List<TweetImage> images = tweetRepository.findImagesByTweetId(tweetEntity.getId());
        List<TweetComment> comments = (tweetRepository.findCommentsByTweetId(tweetEntity.getId()));
        comments.forEach(comment ->
                comment.setSender(userRepository.findSenderById(comment.getSender().getId())));
        return new TweetResponse(
                tweetEntity.getId(),
                tweetEntity.getContent(),
                tweetEntity.getCreatedOn(),
                images,
                sender,
                comments
        );
    }

}