package com.lovingpets.auth_service.persistence.repository;

import com.lovingpets.auth_service.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


}
