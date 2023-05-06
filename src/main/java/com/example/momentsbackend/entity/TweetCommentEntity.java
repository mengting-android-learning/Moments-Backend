package com.example.momentsbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "tweet_comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "tweet_id")
    private Long tweetId;

    @Column(name = "sender_id")
    private Long senderId;
}
