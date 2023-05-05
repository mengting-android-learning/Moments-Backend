package com.example.momentsbackend.web;

import com.example.momentsbackend.service.TweetService;
import com.example.momentsbackend.web.dto.TweetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

}
