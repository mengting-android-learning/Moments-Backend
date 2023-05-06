package com.example.momentsbackend.web;

import com.example.momentsbackend.domain.TweetComment;
import com.example.momentsbackend.domain.Sender;
import com.example.momentsbackend.domain.TweetImage;
import com.example.momentsbackend.service.TweetService;
import com.example.momentsbackend.web.dto.TweetResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(TweetController.class)
public class TweetControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TweetService service;

    @Test
    void should_return_list_with_empty_tweet() throws Exception {
        when(service.findAll()).thenReturn(List.of(new TweetResponse(
                1L,
                null,
                null,
                new ArrayList<TweetImage>(),
                new Sender(1L,null, null, null),
                new ArrayList<TweetComment>()
        )));

        mvc.perform(MockMvcRequestBuilders.get("/tweets"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].images").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sender").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].comments").isEmpty());
    }
}
