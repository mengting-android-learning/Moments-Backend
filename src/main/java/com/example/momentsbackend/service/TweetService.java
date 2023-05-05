package com.example.momentsbackend.service;

import com.example.momentsbackend.domain.Sender;
import com.example.momentsbackend.domain.Comment;
import com.example.momentsbackend.domain.TweetImage;
import com.example.momentsbackend.web.dto.TweetResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TweetService {
    public List<TweetResponse> findAll() {
        return List.of(new TweetResponse(
                null,
                new ArrayList<TweetImage>(),
                new Sender("userName", "nick", "avatar"),
                new ArrayList<Comment>()
        ));
    }
}
