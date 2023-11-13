package com.isa.wildcards.controller;

import com.isa.wildcards.dto.UserDto;
import com.isa.wildcards.entity.User;
import com.isa.wildcards.sevice.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class UserPageController {

    private UserService userService;

    @ModelAttribute("user")
    public User user() {
        return new User();
    }
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
    @PostMapping("/sign-in")
    public String logInUser(@ModelAttribute("user") User user, Model model){
        if (userService.logInUser(user)) {
            return "redirect:/";
        } else {
            model.addAttribute("error", true);
            return "sign-in-page";
        }
    }
}
