package com.example.momentsbackend.mapper;

import com.example.momentsbackend.domain.TweetComment;
import com.example.momentsbackend.domain.TweetImage;
import com.example.momentsbackend.entity.TweetCommentEntity;
import com.example.momentsbackend.entity.TweetImageEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TweetMapper {

    private final ModelMapper mapper;

    public TweetImage toDomain(TweetImageEntity tweetImageEntity) {
        return mapper.map(tweetImageEntity, TweetImage.class);
    }

    public TweetComment toDomain(TweetCommentEntity tweetCommentEntity) {
        return mapper.map(tweetCommentEntity, TweetComment.class);
    }

}
