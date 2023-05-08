package com.example.momentsbackend.web.dto.response;

import com.example.momentsbackend.domain.Sender;
import com.example.momentsbackend.domain.TweetComment;
import com.example.momentsbackend.domain.TweetImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetResponse {

    private Long id;

    private String content;

    private Timestamp createdOn;

    private List<TweetImage> images;

    private Sender sender;

    private List<TweetComment> comments;

}
