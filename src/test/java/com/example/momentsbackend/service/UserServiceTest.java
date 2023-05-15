package com.example.momentsbackend.service;

import com.example.momentsbackend.domain.User;
import com.example.momentsbackend.exception.UserNameExistsException;
import com.example.momentsbackend.repository.UserRepository;
import com.example.momentsbackend.web.dto.request.CreateUserRequest;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    User user = new User(1L, "name", "nick", "avatar", "profile");


    @Test
    void should_save_user_correctly() throws Exception {
        when(userRepository.findUserByName("name")).thenReturn(null);
        when(userRepository.saveUser(Mockito.any(CreateUserRequest.class))).thenReturn(
                user
        );

        User savedUser = userService.saveUser(new CreateUserRequest("profile", "avatar", "nick", "name"));

        assertEquals(savedUser.getId(), 1L);
        assertEquals(savedUser.getUserName(), "name");
    }

    @Test
    void should_throw_exception_when_user_name_exists() {
        when(userRepository.findUserByName("name")).thenReturn(user);

        assertThrows(UserNameExistsException.class, () -> userService.saveUser(new CreateUserRequest("profile", "avatar", "nick", "name")));

        verify(userRepository, times(0)).saveUser(Mockito.any(CreateUserRequest.class));
    }
}
