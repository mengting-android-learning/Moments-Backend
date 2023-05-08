package com.example.momentsbackend.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {
    private Long senderId;
    private Long tweetId;
    private String content;

    private Long createdOn;

}
