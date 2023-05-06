package com.example.momentsbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tweet_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @Column(name = "tweet_id")
    private Long tweetId;
}
