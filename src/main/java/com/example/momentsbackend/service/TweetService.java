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
import com.example.momentsbackend.web.dto.request.CreateTweetRequest;
import com.example.momentsbackend.domain.Tweet;
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

    public List<Tweet> findAll() {
        List<TweetEntity> tweetEntities = tweetRepository.findAll();
        return CollectionUtils.isEmpty(tweetEntities) ?
                Collections.emptyList() :
                tweetEntities.stream()
                        .map(this::getTweetResponse)
                        .toList();
    }

    public Tweet saveTweet(CreateTweetRequest tweetRequest) {
        TweetEntity tweetEntity = tweetRepository.save(new TweetEntity(null, tweetRequest.getContent(), tweetRequest.getCreatedOn(), tweetRequest.getUserId()));
        if (tweetRequest.getImages() != null) {
            saveTweetImages(tweetEntity.getId(), tweetRequest.getImages());
        }
        return getTweetResponse(tweetEntity);
    }

    private void saveTweetImages(Long tweetId, List<TweetImage> images) {
        for (TweetImage image : images) {
            tweetRepository.saveImage(new TweetImageEntity(null, image.getUrl(), tweetId));
        }
    }

    public TweetComment saveComment(String id, CreateCommentRequest commentRequest) {
        TweetComment comment = tweetRepository.saveComment(
                new TweetCommentEntity(null, commentRequest.getContent(),
                        commentRequest.getCreatedOn(), Long.parseLong(id), commentRequest.getSenderId()));
        comment.setSender(getSenderBySenderId(comment.getSender().getId()));
        return comment;
    }

    public boolean deleteTweet(Long id) {
        tweetRepository.deleteCommentsByTweetId(id);
        tweetRepository.deleteImagesByTweetId(id);
        tweetRepository.deleteById(id);
        return true;
    }

    private Tweet getTweetResponse(TweetEntity tweetEntity) {
        List<TweetImage> images = getImagesByTweetId(tweetEntity.getId());
        BaseUser sender = getSenderBySenderId(tweetEntity.getSenderId());
        List<TweetComment> comments = getCommentsByTweetId(tweetEntity.getId());
        return new Tweet(
                tweetEntity.getId(),
                tweetEntity.getContent(),
                tweetEntity.getCreatedOn(),
                images,
                sender,
                comments
        );
    }

    private List<TweetImage> getImagesByTweetId(Long id) {
        return tweetRepository.findImagesByTweetId(id);
    }

    private BaseUser getSenderBySenderId(Long senderId) {
        return userRepository.findSenderById(senderId);
    }

    private List<TweetComment> getCommentsByTweetId(Long id) {
        List<TweetComment> comments = (tweetRepository.findCommentsByTweetId(id));
        comments.forEach(comment ->
                comment.setSender(getSenderBySenderId(comment.getSender().getId())));
        return comments;
    }

}