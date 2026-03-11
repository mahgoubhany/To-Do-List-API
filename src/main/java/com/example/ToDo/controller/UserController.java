package com.example.ToDo.controller;

import com.example.ToDo.dto.LoginRequest;
import com.example.ToDo.dto.AuthResponse;
import com.example.ToDo.dto.RegisterRequest;
import com.example.ToDo.model.User;
import com.example.ToDo.security.AuthenticationService;
import com.example.ToDo.security.TaskUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final AuthenticationService authenticationService;

    public UserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // ✅ Register new user
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // Step 1: Authenticate credentials
        UserDetails userDetails = authenticationService.authenticate(
                request.getUsername(),
                request.getPassword()
        );

        // Step 2: Generate JWT token
        String token = authenticationService.generateToken(userDetails);

        // Step 3: Build response DTO
        AuthResponse response = AuthResponse.builder()
                .token(token)
                .expiresIn(3600) // example: 1 hour expiry
                .build();

        return ResponseEntity.ok(response);
    }


    // ✅ Get current authenticated user profile
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        TaskUserDetails userDetails = (TaskUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(userDetails.getUser());
    }
}
