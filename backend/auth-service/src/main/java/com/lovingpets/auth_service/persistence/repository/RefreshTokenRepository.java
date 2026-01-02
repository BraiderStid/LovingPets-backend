package com.lovingpets.auth_service.persistence.repository;

import com.lovingpets.auth_service.persistence.entity.RefreshTokenEntity;
import com.lovingpets.auth_service.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);
    void deleteByUser(UserEntity user);
}
