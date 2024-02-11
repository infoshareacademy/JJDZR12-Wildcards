package com.isa.wildcards.sevice;

import com.isa.wildcards.dto.UserDto;
import com.isa.wildcards.entity.History;
import com.isa.wildcards.entity.User;
import com.isa.wildcards.entity.UserRole;
import com.isa.wildcards.repository.HistoryRepository;
import com.isa.wildcards.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;
    private final PasswordEncoder passwordEncoder;

    public void createNewUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUserName())) {
            throw new IllegalArgumentException("Username " + userDto.getUserName() + " already exists");
        }
        userRepository.save(new User(userDto.getUserName(), passwordEncoder.encode(userDto.getPassword()), UserRole.USER));
        log.debug("Created user with username: {}", userDto.getUserName());
    }

    private User getUserByUsername(String username) {
        log.debug("Fetching user by username: {}", username);
        return userRepository.findByUsername(username);
    }

    public boolean logInUser(User user){
        User existingUser = getUserByUsername(user.getUsername());
        log.info("Logging in user with username: {}", user.getUsername());
        return existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword());
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }
}