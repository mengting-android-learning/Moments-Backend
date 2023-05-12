package com.example.momentsbackend.repository;

import com.example.momentsbackend.domain.TweetComment;
import com.example.momentsbackend.domain.TweetImage;
import com.example.momentsbackend.entity.TweetCommentEntity;
import com.example.momentsbackend.entity.TweetEntity;
import com.example.momentsbackend.entity.TweetImageEntity;
import com.example.momentsbackend.mapper.TweetMapper;
import com.example.momentsbackend.repository.jpa.JpaTweetCommentRepository;
import com.example.momentsbackend.repository.jpa.JpaTweetImageRepository;
import com.example.momentsbackend.repository.jpa.JpaTweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TweetRepositoryProvider implements TweetRepository {

    private final JpaTweetRepository tweetRepository;
    private final JpaTweetImageRepository imageRepository;
    private final JpaTweetCommentRepository commentRepository;
    private final TweetMapper tweetMapper;

    @Override
    public List<TweetEntity> findAll() {
        return tweetRepository.findAllByOrderByCreatedOnDesc();
    }

    @Override
    public TweetEntity save(TweetEntity tweetEntity) {
        return tweetRepository.save(tweetEntity);
    }

    @Override
    public void deleteById(Long id) {
        tweetRepository.deleteById(id);
    }

    @Override
    public List<TweetImage> findImagesByTweetId(Long id) {
        List<TweetImageEntity> imagesEntities = imageRepository.getImagesByTweetId(id);
        return imagesEntities.stream()
                .map(tweetMapper::toDomain)
                .toList();
    }

    @Override
    public TweetImage saveImage(TweetImageEntity image) {
        TweetImageEntity imageEntity = imageRepository.save(image);
        return tweetMapper.toDomain(imageEntity);
    }

    @Override
    public void deleteImagesByTweetId(Long id) {
        imageRepository.deleteByTweetId(id);

    }

    @Override
    public List<TweetComment> findCommentsByTweetId(Long id) {
        List<TweetCommentEntity> commentEntities = commentRepository.getCommentsByTweetId(id);
        return commentEntities.stream()
                .map(tweetMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteCommentsByTweetId(Long id) {
        commentRepository.deleteByTweetId(id);
    }

    @Override
    public TweetComment saveComment(TweetCommentEntity tweetCommentEntity) {
        TweetCommentEntity commentEntity = commentRepository.save(tweetCommentEntity);
        return tweetMapper.toDomain(commentEntity);
    }
}
