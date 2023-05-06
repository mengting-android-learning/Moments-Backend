package com.example.momentsbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class Comment {
    private Long id;
    private String content;
    private Timestamp createdOn;
    private Sender sender;
}
