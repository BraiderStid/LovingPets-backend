package com.lovingpets.auth_service.persistence.repository;

import com.lovingpets.auth_service.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
    Optional<UserEntity> findByEmail(String email);

    @Query("""
        SELECT u
        FROM UserEntity u
        JOIN FETCH u.roles ur
        JOIN FETCH ur.role r
        WHERE u.email = :email
    """)
    Optional<UserEntity> findByEmailWithRoles(@Param("email") String email);

}
