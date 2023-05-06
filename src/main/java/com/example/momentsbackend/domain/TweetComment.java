package com.example.momentsbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetComment {
    private Long id;
    private String content;
    private Timestamp createdOn;
    private Sender sender;
}
