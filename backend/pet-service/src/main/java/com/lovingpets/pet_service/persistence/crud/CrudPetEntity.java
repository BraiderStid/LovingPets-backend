package com.lovingpets.pet_service.persistence.crud;

import com.lovingpets.pet_service.persistence.entity.PetEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudPetEntity extends CrudRepository<PetEntity, Long> {

}
