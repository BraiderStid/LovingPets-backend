package com.lovingpets.auth_service.persistence.repository;

import com.lovingpets.auth_service.domain.model.RoleName;
import com.lovingpets.auth_service.persistence.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(RoleName name);

}
