package com.example.momentsbackend.web.dto;

import com.example.momentsbackend.domain.Sender;
import com.example.momentsbackend.domain.Comment;
import com.example.momentsbackend.domain.TweetImage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TweetResponse {

    private String content;

    private List<TweetImage> images;

    private Sender sender;

    private List<Comment> comments;

}
