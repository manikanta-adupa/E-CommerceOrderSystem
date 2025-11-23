package com.learning.ecommerceordersystem.controller;

import com.learning.ecommerceordersystem.dto.JwtResponse;
import com.learning.ecommerceordersystem.dto.LoginRequest;
import com.learning.ecommerceordersystem.dto.RegisterRequest;
import com.learning.ecommerceordersystem.enums.Role;
import com.learning.ecommerceordersystem.model.User;
import com.learning.ecommerceordersystem.repository.UserRepository;
import com.learning.ecommerceordersystem.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        if(userRepository.existsByUsername(registerRequest.getUsername())){
            return ResponseEntity.badRequest().body("Username is already in use");
        }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setRole(Role.CUSTOMER);

        userRepository.save(user);

        return ResponseEntity.ok("user registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        // 1. Trigger Authentication (Checks password)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // 2. If valid, set context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Generate Token
        String jwt = jwtUtils.generateToken(loginRequest.getUsername());

        // 4. Return Token
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
