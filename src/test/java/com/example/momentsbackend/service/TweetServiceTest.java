package com.example.momentsbackend.service;

import com.example.momentsbackend.repository.jpa.JpaTweetCommentRepository;
import com.example.momentsbackend.repository.jpa.JpaTweetImageRepository;
import com.example.momentsbackend.repository.jpa.JpaTweetRepository;
import com.example.momentsbackend.repository.jpa.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TweetServiceTest {

    @InjectMocks
    private TweetService tweetService;

    @Mock
    private JpaTweetRepository tweetRepository;

    @Mock
    private JpaTweetImageRepository tweetImageRepository;

    @Mock
    private JpaTweetCommentRepository tweetCommentRepository;

    @Mock
    private JpaUserRepository userRepository;

    @BeforeEach
    void setUp(){

    }

    void should_return_tweets_list(){

    }
}
