package com.example.momentsbackend.web;

import com.example.momentsbackend.domain.Sender;
import com.example.momentsbackend.domain.TweetComment;
import com.example.momentsbackend.domain.TweetImage;
import com.example.momentsbackend.service.TweetService;
import com.example.momentsbackend.web.dto.request.CreateCommentRequest;
import com.example.momentsbackend.web.dto.request.CreateTweetImagesRequest;
import com.example.momentsbackend.web.dto.request.CreateTweetRequest;
import com.example.momentsbackend.web.dto.response.TweetResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(TweetController.class)
public class TweetControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TweetService service;

    private static TweetResponse tweet;

    private static CreateTweetRequest request;

    private static ObjectMapper mapper;

    private static TweetComment comment;
    private static CreateCommentRequest commentRequest;

    @Test
    void should_return_tweet_list_when_find_all() throws Exception {
        when(service.findAll()).thenReturn(List.of(tweet));

        mvc.perform(MockMvcRequestBuilders.get("/tweets"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].images").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sender").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].comments").isEmpty());

        verify(service).findAll();
    }

    @Test
    void should_return_saved_tweet_when_save_new_tweet() throws Exception {
        when(service.saveTweet(Mockito.any(CreateTweetRequest.class))).thenReturn(tweet);

        mvc.perform(MockMvcRequestBuilders.post("/tweets")
                        .accept(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("content"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.images").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sender").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.comments").isEmpty());
    }

    @Test
    void should_throw_exception_when_save_tweet_request_is_invalid() throws Exception {

        request.setUserId(null);

        mvc.perform(MockMvcRequestBuilders.post("/tweets")
                        .accept(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("userId:must not be null"));
    }

    @Test
    void should_return_saved_comment_when_save_new_tweet() throws Exception {
        when(service.saveComment(Mockito.any(CreateCommentRequest.class))).thenReturn(comment);

        mvc.perform(MockMvcRequestBuilders.post("/tweets/comments")
                        .accept(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(commentRequest))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("content"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sender").exists());
    }

    @Test
    void should_throw_exception_when_save_comment_request_is_invalid() throws Exception {

        commentRequest.setTweetId(null);

        mvc.perform(MockMvcRequestBuilders.post("/tweets/comments")
                        .accept(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(commentRequest))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("tweetId:must not be null"));
    }


    @BeforeEach
    void setup() {

        tweet = new TweetResponse(
                1L,
                "content",
                1683863092647L,
                List.of(new TweetImage("url")),
                new Sender(1L, "userName", "nick", "avatar"),
                Collections.emptyList()
        );

        request = new CreateTweetRequest(1L,
                "content",
                1683863092647L,
                List.of(new CreateTweetImagesRequest("url")));

        mapper = new ObjectMapper();

        comment = new TweetComment(1L,
                "content",
                1683863092647L,
                new Sender(1L, "userName", "nick", "avatar"));

        commentRequest = new CreateCommentRequest(1L, 1L, 1683863092647L, "content");


    }
}
