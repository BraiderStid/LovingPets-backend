package com.lovingpets.auth_service.persistence.repository;

import com.lovingpets.auth_service.persistence.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {


}
