package com.isa.wildcards.service;

import com.isa.wildcards.dto.UserDto;
import com.isa.wildcards.entity.History;
import com.isa.wildcards.entity.User;
import com.isa.wildcards.repository.HistoryRepository;
import com.isa.wildcards.repository.UserRepository;
import com.isa.wildcards.sevice.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private HistoryRepository historyRepository;

    @Test
    void createNewUserSuccessful() {

        UserDto userDto = new UserDto();
        userDto.setUserName("newUser");
        userDto.setPassword("password");


        Mockito.when(userRepository.existsByUsername("newUser")).thenReturn(false);

        userService.createNewUser(userDto);

        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }

    @Test
    void createNewUserUsernameAlreadyExists() {

        UserDto userDto = new UserDto();
        userDto.setUserName("existingUser");
        userDto.setPassword("password");

        Mockito.when(userRepository.existsByUsername("existingUser")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> userService.createNewUser(userDto));
    }

    @Test
    void logInUserSuccessful() {

        String username = "testUser";
        String password = "password123";
        Mockito.lenient().when(userRepository.existsByUsername(username)).thenReturn(true);
        Mockito.when(userRepository.findByUsername(username)).thenReturn(new User(username, password));

        boolean result = userService.logInUser(new User(username, password));

        assertTrue(result);
    }

    @Test
    void logInUserUserNotFound() {

        String username = "nonExistentUser";
        String password = "password123";
        Mockito.lenient().when(userRepository.existsByUsername(username)).thenReturn(false);

        boolean result = userService.logInUser(new User(username, password));

        assertFalse(result);
    }

    @Test
    void logInUserIncorrectPassword() {

        String username = "testUser";
        String correctPassword = "password123";
        String incorrectPassword = "wrongPassword";
        Mockito.lenient().when(userRepository.existsByUsername(username)).thenReturn(true);
        Mockito.when(userRepository.findByUsername(username)).thenReturn(new User(username, correctPassword));

        boolean result = userService.logInUser(new User(username, incorrectPassword));

        assertFalse(result);
    }

    @Test
    void findAllByUserSuccessful() {

        User user = new User("testUser", "password");
        userRepository.save(user);

        History history1 = new History("Event 1", user);
        History history2 = new History("Event 2", user);
        historyRepository.saveAll(List.of(history1, history2));

        List<History> histories = historyRepository.findAllByUser(user);

        assertEquals(2, histories.size());
        assertTrue(histories.contains(history1));
        assertTrue(histories.contains(history2));
    }

    @Test
    void findAllByUserUserNotFound() {

        User nonExistentUser = new User("nonExistentUser", "password");

        List<History> histories = historyRepository.findAllByUser(nonExistentUser);

        assertTrue(histories.isEmpty());
    }
}

