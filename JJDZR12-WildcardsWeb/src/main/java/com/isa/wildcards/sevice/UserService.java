package com.isa.wildcards.sevice;

import com.isa.wildcards.dto.UserDto;
import com.isa.wildcards.entity.History;
import com.isa.wildcards.entity.User;
import com.isa.wildcards.repository.HistoryRepository;
import com.isa.wildcards.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;

    public void createNewUser(UserDto userDto){
        if(userRepository.existsByUsername(userDto.getUserName())){
            throw new IllegalArgumentException("Username " + userDto.getUserName() + " already exists");
        }
        userRepository.save(new User(userDto.getUserName(),userDto.getPassword()));
    }

    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean logInUser(User user){
        User existingUser = getUserByUsername(user.getUsername());
        return existingUser != null && user.getPassword().equals(existingUser.getPassword());
    }

    public List<History> findAllByUser(final User user) {
        return historyRepository.findAllByUser(userRepository.findByUsername(user.getUsername()));
    }
}