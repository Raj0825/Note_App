package com.raj.note.app.feature.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;   // ✔ FIELD EXISTS

    public AuthController(UserService userService) {   // ✔ INJECTED
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);   // ✔ USE INSTANCE
    }
}