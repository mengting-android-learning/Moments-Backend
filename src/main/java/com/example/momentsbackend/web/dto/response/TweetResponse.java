package com.example.momentsbackend.web.dto.response;

import com.example.momentsbackend.domain.BaseUser;
import com.example.momentsbackend.domain.TweetComment;
import com.example.momentsbackend.domain.TweetImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetResponse {

    private Long id;

    private String content;

    private Long createdOn;

    private List<TweetImage> images;

    private BaseUser sender;

    private List<TweetComment> comments;

}
