package com.grok.hunt.Services;



import com.grok.hunt.Models.Users;
import com.grok.hunt.Repository.UserRepo;
import com.grok.hunt.config.UserAlreadyExistsException;
import com.grok.hunt.dtos.LoginRequest;
import com.grok.hunt.dtos.LoginResponse;
import com.grok.hunt.dtos.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(RegisterRequest registerRequest){
        if (repo.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists: " + registerRequest.getUsername());
        }

        Users user = new Users();

        user.setUsername(registerRequest.getUsername());
        user.setPassword(encoder.encode(registerRequest.getPassword()));
        return repo.save(user);
    }

    public LoginResponse verify(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtService.generateToken(authentication);
            String status = "success";
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setStatus(status);
            return loginResponse;
        } catch (RuntimeException e) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(null);
            loginResponse.setStatus("failed");
            return loginResponse;
        }
    }
}
