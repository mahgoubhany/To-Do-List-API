package com.example.ToDo.security;

import com.example.ToDo.dto.AuthResponse;
import com.example.ToDo.dto.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    UserDetails authenticate(String username, String password);
    String generateToken(UserDetails userDetails);
    UserDetails validateToken(String token);
    AuthResponse register(RegisterRequest request);
}
