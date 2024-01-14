package com.isa.wildcards.sevice;

import com.isa.wildcards.dto.UserDto;
import com.isa.wildcards.entity.History;
import com.isa.wildcards.entity.User;
import com.isa.wildcards.repository.HistoryRepository;
import com.isa.wildcards.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;

    public void createNewUser(UserDto userDto){
        log.info("Creating a new user with username: {}", userDto.getUserName());

        if(userRepository.existsByUsername(userDto.getUserName())){
            throw new IllegalArgumentException("Username " + userDto.getUserName() + " already exists");
        }
        userRepository.save(new User(userDto.getUserName(),userDto.getPassword()));

        log.info("User created successfully.");
    }

    private User getUserByUsername(String username) {
        log.debug("Fetching user by username: {}", username);
        return userRepository.findByUsername(username);
    }

    public boolean logInUser(User user){
        log.info("Logging in user with username: {}", user.getUsername());

        User existingUser = getUserByUsername(user.getUsername());

        boolean isLoggedIn = existingUser != null && user.getPassword().equals(existingUser.getPassword());

        if (!isLoggedIn) {
            log.warn("Login failed for user with username: {}", user.getUsername());
        }

        return isLoggedIn;
    }


    public List<History> findAllByUser(final User user) {
        log.info("Finding all history records for user with username: {}", user.getUsername());

        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser == null) {
            log.warn("User with username {} not found. Cannot fetch history records.", user.getUsername());
            return List.of();
        }

        return historyRepository.findAllByUser(existingUser);
    }
}