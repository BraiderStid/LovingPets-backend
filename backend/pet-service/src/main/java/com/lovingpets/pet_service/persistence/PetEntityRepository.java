package com.lovingpets.pet_service.persistence;

import com.lovingpets.pet_service.domain.dto.PetDto;
import com.lovingpets.pet_service.domain.dto.PetUpdateDto;
import com.lovingpets.pet_service.domain.repository.PetRepository;
import com.lovingpets.pet_service.persistence.crud.CrudPetEntity;
import com.lovingpets.pet_service.persistence.entity.PetEntity;
import com.lovingpets.pet_service.persistence.mapper.PetMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetEntityRepository implements PetRepository {

    private final CrudPetEntity crudPetEntity;
    private final PetMapper petMapper;

    public PetEntityRepository(CrudPetEntity crudPetEntity, PetMapper petMapper) {
        this.crudPetEntity = crudPetEntity;
        this.petMapper = petMapper;
    }

    @Override
    public List<PetDto> getAll() {
        return this.petMapper.toDto(this.crudPetEntity.findAll());
    }

    @Override
    public PetDto getById(long id) {
        PetEntity petEntity = this.crudPetEntity.findById(id).orElse(null);
        return this.petMapper.toDto(petEntity);
    }

    @Override
    public PetDto save(PetDto petDto) {
        PetEntity petEntity = this.petMapper.toEntity(petDto);
        PetEntity savedEntity = this.crudPetEntity.save(petEntity);

        return this.petMapper.toDto(savedEntity);
    }

    @Override
    public PetDto update(long id, PetUpdateDto petUpdateDto) {

        PetEntity petEntity = this.crudPetEntity.findById(id).orElse(null);
        if (petEntity == null) return null;

        this.petMapper.updateEntityFromDto(petUpdateDto, petEntity );

        PetEntity updatedEntity = this.crudPetEntity.save(petEntity);

        return this.petMapper.toDto(updatedEntity);
    }

    @Override
    public boolean delete(long id) {
        PetEntity petEntity = this.crudPetEntity.findById(id).orElse(null);

        if (petEntity == null) return false;

        this.crudPetEntity.delete(petEntity);

        return true;
    }

}
