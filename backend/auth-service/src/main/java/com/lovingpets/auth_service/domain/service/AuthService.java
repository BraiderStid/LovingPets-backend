package com.lovingpets.auth_service.domain.service;

import com.lovingpets.auth_service.config.JwtUtil;
import com.lovingpets.auth_service.domain.dto.AuthResponseDto;
import com.lovingpets.auth_service.persistence.entity.RefreshTokenEntity;
import com.lovingpets.auth_service.persistence.entity.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserSecurityService userSecurityService;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public AuthService(
            AuthenticationManager authenticationManager,
            UserSecurityService userSecurityService,
            JwtUtil jwtUtil,
            RefreshTokenService refreshTokenService
    ) {
        this.authenticationManager = authenticationManager;
        this.userSecurityService = userSecurityService;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
    }

    public AuthResponseDto login(String email, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        UserEntity user = userSecurityService.getUserEntityByEmail(email);

        List<String> roles = user.getRoles()
                .stream()
                .map(userRole -> userRole.getRole().getName().name())
                .toList();

        String accessToken = jwtUtil.create(user.getId(), user.getEmail(), roles);

        RefreshTokenEntity refreshToken = refreshTokenService.createRefreshToken(user);

        return new AuthResponseDto(accessToken, refreshToken.getToken());
    }

    public AuthResponseDto refreshToken(String refreshTokenStr) {

        RefreshTokenEntity refreshToken = refreshTokenService
                .getByToken(refreshTokenStr)
                .orElseThrow(() -> new RuntimeException("Refresh invalid token"));

        refreshTokenService.verifyExpiration(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh expired token"));

        UserEntity user = refreshToken.getUser();

        List<String> roles = user.getRoles()
                .stream()
                .map(userRole -> userRole.getRole().getName().name())
                .toList();

        String newAccessToken = jwtUtil.create(user.getId(), user.getEmail(), roles);

        return new AuthResponseDto(newAccessToken, refreshTokenStr);
    }
}