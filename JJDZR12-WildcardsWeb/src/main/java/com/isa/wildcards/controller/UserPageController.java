package com.isa.wildcards.controller;

import com.isa.wildcards.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserPageController {
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

}

