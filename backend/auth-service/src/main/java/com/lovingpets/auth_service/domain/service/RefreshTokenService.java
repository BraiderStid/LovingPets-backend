package com.lovingpets.auth_service.domain.service;

import com.lovingpets.auth_service.persistence.entity.RefreshTokenEntity;
import com.lovingpets.auth_service.persistence.entity.UserEntity;
import com.lovingpets.auth_service.persistence.repository.RefreshTokenRepository;
import com.lovingpets.auth_service.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${app.jwt.refresh-expiration-ms}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshTokenEntity createRefreshToken(UserEntity user) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plusNanos(refreshTokenDurationMs * 1_000_000));
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshTokenEntity> verifyExpiration(RefreshTokenEntity token) {
        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(token);
            return Optional.empty();
        }
        return Optional.of(token);
    }

    public void deleteByUser(UserEntity user) {
        refreshTokenRepository.deleteByUser(user);
    }

    public Optional<RefreshTokenEntity> getByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


}