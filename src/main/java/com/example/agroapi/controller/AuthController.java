package com.example.agroapi.controller;

import com.example.agroapi.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto user) {
        System.out.println("== LOGIN == Usuario: " + user.getUsername() + " / " + user.getPassword());
        if ("root".equals(user.getUsername()) && "1234".equals(user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(new TokenResponse(token));
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    public static class UserDto {
        private String username;
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public record TokenResponse(String token) {}
}
