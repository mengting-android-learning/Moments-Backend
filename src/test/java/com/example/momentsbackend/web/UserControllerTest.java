package com.example.momentsbackend.web;

import com.example.momentsbackend.domain.User;
import com.example.momentsbackend.service.UserService;
import com.example.momentsbackend.web.dto.request.CreateUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    private static CreateUserRequest userRequest;

    private static User user;

    private static ObjectMapper mapper;

    @Test
    void should_return_saved_user() throws Exception {
        when(service.saveUser(Mockito.any(CreateUserRequest.class))).thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.post("/users")
                        .accept(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userRequest))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.profileImage").value("profile"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.avatar").value("avatar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nick").value("nick"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("name"));
    }

    @Test
    void should_throw_exception_when_create_user_request_invalid() throws Exception {

        userRequest.setUserName(null);

        mvc.perform(MockMvcRequestBuilders.post("/users")
                        .accept(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userRequest))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists());

    }

    @Test
    void should_return_found_user_by_given_user_name() throws Exception {
        when(service.findUserByName("name")).thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.get("/users?name=name"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.profileImage").value("profile"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.avatar").value("avatar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nick").value("nick"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("name"));
    }

    @Test
    void should_throw_exception_when_found_no_user_by_given_user_name() throws Exception {
        when(service.findUserByName("name")).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/users?name=name"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("this user does not exist name:name"));
    }

    @Test
    void should_throw_exception_when_given_user_name_is_blank() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/users?name="))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists());
    }


    @BeforeEach
    void setUp() {
        userRequest = new CreateUserRequest("profile", "avatar", "nick", "name");
        user = new User(1L, "profile", "avatar", "nick", "name");
        mapper = new ObjectMapper();
    }

}
