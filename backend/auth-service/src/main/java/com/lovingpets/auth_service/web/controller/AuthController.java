package com.lovingpets.auth_service.web.controller;

import com.lovingpets.auth_service.domain.dto.AuthResponseDto;
import com.lovingpets.auth_service.domain.dto.LoginDto;
import com.lovingpets.auth_service.domain.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        AuthResponseDto tokens = authService.login(loginDto.getEmail(), loginDto.getPassword());
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        AuthResponseDto tokens = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(tokens);
    }
}
