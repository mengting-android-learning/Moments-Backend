package com.example.momentsbackend.web;

import com.example.momentsbackend.domain.TweetComment;
import com.example.momentsbackend.service.TweetService;
import com.example.momentsbackend.web.dto.request.CreateCommentRequest;
import com.example.momentsbackend.web.dto.request.CreateTweetRequest;
import com.example.momentsbackend.web.dto.response.TweetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tweets")
@RequiredArgsConstructor
public class TweetController {
    private final TweetService service;

    @GetMapping
    public List<TweetResponse> fetchTweets() {
        return service.findAll();
    }

    @PostMapping
    public TweetResponse saveTweet(@RequestBody CreateTweetRequest tweetRequest) {
        return service.saveTweet(tweetRequest);
    }

    @PostMapping("/comments")
    public TweetComment saveComment(@RequestBody CreateCommentRequest commentRequest) {
        return service.saveComment(commentRequest);
    }

}
