package com.example.momentsbackend.repository.jpa;

import com.example.momentsbackend.domain.Tweet;
import com.example.momentsbackend.entity.TweetCommentEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class JpaTweetCommentRepositoryTest {

    @Autowired
    private JpaTweetCommentRepository tweetCommentRepository;

    @Test
    @Sql("/sql/insert_into_two_tweet_comments.sql")
    void should_return_comments_by_tweet_id_correctly() {
        List<TweetCommentEntity> comments = tweetCommentRepository.getCommentsByTweetId(1L);
        assertEquals(comments.size(), 2);
    }

    @Test
    @Sql("/sql/insert_into_two_tweet_comments.sql")
    void should_return_correct_size_after_delete() {
        tweetCommentRepository.deleteByTweetId(1L);
        List<TweetCommentEntity> comments = tweetCommentRepository.findAll();
        assertEquals(comments.size(), 0);
    }

    @Test
    void should_return_saved_comment_correctly() {
        TweetCommentEntity comment = new TweetCommentEntity(null, "content", 1683898255986L, 1L, 1L);

        tweetCommentRepository.save(comment);

        List<TweetCommentEntity> comments = tweetCommentRepository.findAll();

        assertEquals(comments.size(), 1);
        assertEquals(comments.get(0).getId(), 1L);
        assertEquals(comments.get(0).getContent(), "content");

    }
}
