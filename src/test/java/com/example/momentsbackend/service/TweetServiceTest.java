package com.example.momentsbackend.service;

import com.example.momentsbackend.domain.Sender;
import com.example.momentsbackend.domain.TweetComment;
import com.example.momentsbackend.entity.TweetCommentEntity;
import com.example.momentsbackend.entity.TweetEntity;
import com.example.momentsbackend.entity.TweetImageEntity;
import com.example.momentsbackend.repository.TweetRepository;
import com.example.momentsbackend.repository.UserRepository;
import com.example.momentsbackend.web.dto.request.CreateCommentRequest;
import com.example.momentsbackend.web.dto.request.CreateTweetImagesRequest;
import com.example.momentsbackend.web.dto.request.CreateTweetRequest;
import com.example.momentsbackend.web.dto.response.TweetResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TweetServiceTest {

    @InjectMocks
    private TweetService tweetService;
    @Mock
    private TweetRepository tweetRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    void should_return_empty_tweets_list() {
        when(tweetRepository.findAll()).thenReturn(Collections.emptyList());
        List<TweetResponse> tweets = tweetService.findAll();
        assertEquals(tweets, Collections.emptyList());
    }

    @Test
    void should_return_tweets_list() {
        when(tweetRepository.findAll()).thenReturn(List.of(
                new TweetEntity(1L, "content", System.currentTimeMillis(), 1L)
        ));
        when(tweetRepository.findImagesByTweetId(1L)).thenReturn(Collections.emptyList());
        when(tweetRepository.findCommentsByTweetId(1L)).thenReturn(
                List.of(
                        new TweetComment(1L,
                                "content",
                                System.currentTimeMillis(),
                                new Sender(1L, null, null, null))
                )
        );
        when(userRepository.findSenderById(1L)).thenReturn(new Sender(1L, "name", "nick", "avatar"));

        List<TweetResponse> tweets = tweetService.findAll();

        assertEquals(tweets.size(), 1);
        assertEquals(tweets.get(0).getContent(), "content");
        assertEquals(tweets.get(0).getImages(), Collections.emptyList());
        assertEquals(tweets.get(0).getSender().getId(), 1L);
        assertEquals(tweets.get(0).getComments().size(), 1);
        verify(tweetRepository).findAll();
        verify(tweetRepository).findImagesByTweetId(1L);
        verify(tweetRepository).findCommentsByTweetId(1L);
        verify(userRepository, times(2)).findSenderById(1L);
    }

    @Test
    void should_return_tweet_without_images() {
        when(tweetRepository
                .save(Mockito.any(TweetEntity.class)))
                .thenReturn(new TweetEntity(1L, "content", System.currentTimeMillis(), 1L));
        when(tweetRepository.findImagesByTweetId(1L)).thenReturn(Collections.emptyList());
        when(tweetRepository.findCommentsByTweetId(1L)).thenReturn(Collections.emptyList());
        when(userRepository.findSenderById(1L)).thenReturn(new Sender(1L, "name", "nick", "avatar"));

        TweetResponse tweetResponse = tweetService.saveTweet(new CreateTweetRequest(1L,
                null,
                null,
                null));

        assertEquals(tweetResponse.getId(), 1L);
        assertEquals(tweetResponse.getImages(), Collections.emptyList());
        assertEquals(tweetResponse.getComments(), Collections.emptyList());
        verify(tweetRepository, times(0)).saveImage(Mockito.any());
    }

    @Test
    void should_return_tweet_with_images() {

        when(tweetRepository
                .save(Mockito.any(TweetEntity.class)))
                .thenReturn(new TweetEntity(1L, "content", System.currentTimeMillis(), 1L));
        when(tweetRepository.findImagesByTweetId(1L)).thenReturn(Collections.emptyList());
        when(tweetRepository.findCommentsByTweetId(1L)).thenReturn(Collections.emptyList());
        when(tweetRepository.saveImage(Mockito.any(TweetImageEntity.class))).thenReturn(null);
        when(userRepository.findSenderById(1L)).thenReturn(new Sender(1L, "name", "nick", "avatar"));


        TweetResponse tweetResponse = tweetService.saveTweet(new CreateTweetRequest(1L,
                null,
                null,
                List.of(new CreateTweetImagesRequest("url"))));


        assertEquals(tweetResponse.getId(), 1L);
        assertEquals(tweetResponse.getImages(), Collections.emptyList());
        assertEquals(tweetResponse.getComments(), Collections.emptyList());
        verify(tweetRepository, times(1)).saveImage(Mockito.any());
    }

    @Test
    void should_return_saved_comment() {
        when(tweetRepository.saveComment(Mockito.any(TweetCommentEntity.class))).thenReturn(
                new TweetComment(
                        1L,
                        "content",
                        null,
                        new Sender(1L, null, null, null)
                )
        );
        when(userRepository.findSenderById(1L)).thenReturn(new Sender(1L, "name", "nick", "avatar"));

        TweetComment comment = tweetService.saveComment(new CreateCommentRequest(1L, 1L, null, "content"));

        assertEquals(comment.getId(), 1L);
        assertEquals(comment.getSender().getNick(), "nick");
    }

    @Test
    void should_delete_success() {
        Mockito.doNothing().when(tweetRepository).deleteImagesByTweetId(1L);
        Mockito.doNothing().when(tweetRepository).deleteCommentsByTweetId(1L);
        Mockito.doNothing().when(tweetRepository).deleteById(1L);

        boolean success = tweetService.deleteTweet(1L);

        assertTrue(success);
    }
}
