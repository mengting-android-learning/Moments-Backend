package com.example.momentsbackend.web.dto.request;

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
    private Long userId;

    private String content;

    private Long createdOn;

    private List<CreateTweetImagesRequest> images;
}
