package com.kotyk.demo.musicStreaming3.controllers;

import com.kotyk.demo.musicStreaming3.entities.Role;
import com.kotyk.demo.musicStreaming3.entities.User;
import com.kotyk.demo.musicStreaming3.helper.AuthenticationHelper;
import com.kotyk.demo.musicStreaming3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationHelper authenticationHelper) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationHelper = authenticationHelper;
    }

    @PostMapping("/save")
    public String saveUser(Model model,
                           @RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password) {
        User user = new User();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setUsername(username);
        user.setEmail(email);
        User savedUser = userService.saveToDB(user);
        model.addAttribute("user", savedUser);
        return "login";
    }

    @GetMapping("/userPage")
    public String getById(Model model) {
        User userById = userService.getById(authenticationHelper.getUserFromSession().getId());
            model.addAttribute("user", userById);
        return "userPage";
    }

}
