package com.lovingpets.auth_service.persistence.repository;

import com.lovingpets.auth_service.persistence.entity.RoleEntity;
import com.lovingpets.auth_service.persistence.entity.UserEntity;
import com.lovingpets.auth_service.persistence.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    boolean existsByUserAndRole(UserEntity user, RoleEntity role);

}
