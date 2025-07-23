package com.grok.hunt.Controllers;


import com.grok.hunt.Models.Users;
import com.grok.hunt.Services.UserService;
import com.grok.hunt.dtos.LoginRequest;
import com.grok.hunt.dtos.LoginResponse;
import com.grok.hunt.dtos.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return userService.verify(loginRequest);
    }
}
