package com.example.momentsbackend.web.dto.request;

import jakarta.validation.constraints.NotBlank;
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

    private List<CreateTweetImagesRequest> images;

}
