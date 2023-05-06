package com.example.momentsbackend.mapper;

import com.example.momentsbackend.domain.TweetComment;
import com.example.momentsbackend.entity.TweetCommentEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TweetMapperTest {
    //    @Autowired
    private ModelMapper mapper = new ModelMapper();
    private TweetMapper tweetMapper = new TweetMapper(mapper);

    @Test
    void should_map_comment_correctly() {
        TweetCommentEntity commentEntity = new TweetCommentEntity(
                1L,
                "content",
                null,
                1L,
                1L
        );
        TweetComment comment = tweetMapper.toDomain(commentEntity);
        Assertions.assertEquals(comment.getId(), commentEntity.getId());
        Assertions.assertNull(comment.getSender().getNick());
    }
}
