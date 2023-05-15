package com.example.momentsbackend.web.dto.request;

import com.example.momentsbackend.domain.TweetImage;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTweetRequest {

    @NotNull
    private Long userId;

    private String content;

    private Long createdOn = System.currentTimeMillis();

    private List<TweetImage> images;

}
