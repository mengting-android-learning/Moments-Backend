package com.example.momentsbackend.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

    @NotNull
    private Long senderId;

    @NotNull
    private Long tweetId;

    private Long createdOn = System.currentTimeMillis();

    @NotNull
    @NotBlank
    private String content;

}
