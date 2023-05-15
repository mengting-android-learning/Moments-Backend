package com.example.momentsbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetComment {
    private Long id;
    private String content;
    private Long createdOn;
    private BaseUser sender;
}
