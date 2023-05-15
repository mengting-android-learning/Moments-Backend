package com.example.momentsbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {

    private Long id;

    private String content;

    private Long createdOn;

    private List<TweetImage> images;

    private BaseUser sender;

    private List<TweetComment> comments;

}
