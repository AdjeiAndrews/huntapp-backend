package com.grok.hunt.dtos;

import lombok.Getter;
import lombok.Setter;
import com.grok.hunt.Models.Users; // <-- Add this import


@Getter
@Setter
public class LoginResponse {
    private String token;
    private String status;
    private Users user;
}
