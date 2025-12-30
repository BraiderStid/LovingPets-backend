package com.lovingpets.pet_service.domain.repository;

import com.lovingpets.pet_service.domain.dto.PetDto;
import com.lovingpets.pet_service.domain.dto.PetUpdateDto;

import java.util.List;

public interface PetRepository {
    List<PetDto> getAll();
    PetDto getById(long id);
    PetDto save(PetDto petDto);
    PetDto update(long id, PetUpdateDto petUpdateDto);
    boolean delete(long id);
}
