package com.example.momentsbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class Comment {
    private Long id;
    private String content;
    private Timestamp timestamp;
    private Sender sender;
}
