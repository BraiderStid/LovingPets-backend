package com.lovingpets.user_service.persistence.repository;

import com.lovingpets.user_service.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

}