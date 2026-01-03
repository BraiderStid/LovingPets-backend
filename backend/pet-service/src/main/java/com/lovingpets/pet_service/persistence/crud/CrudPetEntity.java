package com.lovingpets.pet_service.persistence.crud;

import com.lovingpets.pet_service.persistence.entity.PetEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudPetEntity extends CrudRepository<PetEntity, Long> {
    List<PetEntity> findByOwnerId(Long ownerId);
}
