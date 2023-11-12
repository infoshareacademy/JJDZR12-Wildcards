package com.isa.wildcards.controller;

import com.isa.wildcards.dto.UserDto;
import com.isa.wildcards.sevice.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserPageController {

    private UserService userService;
    @GetMapping("/user")
    public String getMainUserPage() {
        return "user-main-page";
    }
    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration-page";
    }
    @GetMapping("/sign-in")
    public String getSignInPage() {
        return "sign-in-page";
    }
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("newUser") UserDto userDto){
        userService.createNewUser(userDto);
        return "redirect:/";
    }
}

