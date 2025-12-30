package com.lovingpets.pet_service.domain.service;

import com.lovingpets.pet_service.domain.dto.PetDto;
import com.lovingpets.pet_service.domain.dto.PetUpdateDto;
import com.lovingpets.pet_service.domain.exception.InvalidPetDataException;
import com.lovingpets.pet_service.domain.exception.PetNotFoundException;
import com.lovingpets.pet_service.domain.repository.PetRepository;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<PetDto> getAll(){
        return this.petRepository.getAll();
    }

    @Tool("Retrieve the pet's data to provide recommendations.")
    public PetDto getById(long id) {
        PetDto pet = this.petRepository.getById(id);
        if (pet == null) {
            throw new PetNotFoundException(id);
        }
        return pet;
    }

    public PetDto save(PetDto petDto){
        validatePetData(petDto);
        return this.petRepository.save(petDto);
    }

    public PetDto update(long id, PetUpdateDto petUpdateDto) {
        PetDto existing = this.petRepository.getById(id);
        if (existing == null) {
            throw new PetNotFoundException(id);
        }

        validatePetDataUpdate(petUpdateDto);
        return this.petRepository.update(id, petUpdateDto);
    }

    public boolean delete(long id) {
        PetDto existing = this.petRepository.getById(id);
        if (existing == null) {
            throw new PetNotFoundException(id);
        }
        return this.petRepository.delete(id);
    }

    public String formatPetDataForAI(PetDto pet) {
        if (pet == null) return "";
        return String.format(
                "Species: %s, Breed: %s, Age: %d, Weight: %.2f, OwnerId: %d",
                pet.species(),
                pet.breed(),
                pet.age(),
                pet.weight(),
                pet.ownerId()
        );
    }

    private void validatePetData(PetDto petDto) {
        if (petDto.name() == null || petDto.name().isBlank()) {
            throw new InvalidPetDataException("Pet name cannot be empty");
        }

        if (petDto.age() != null && petDto.age() < 0) {
            throw new InvalidPetDataException("Pet age cannot be negative");
        }

        if (petDto.weight() != null && petDto.weight() <= 0) {
            throw new InvalidPetDataException("Pet weight must be greater than zero");
        }

        if (petDto.species() == null) {
            throw new InvalidPetDataException("Pet species cannot be null");
        }
    }

    private void validatePetDataUpdate(PetUpdateDto petUpdateDto) {
        if (petUpdateDto.name() != null && petUpdateDto.name().isBlank()) {
            throw new InvalidPetDataException("Pet name cannot be empty");
        }

        if (petUpdateDto.age() != null && petUpdateDto.age() < 0) {
            throw new InvalidPetDataException("Pet age cannot be negative");
        }

        if (petUpdateDto.weight() != null && petUpdateDto.weight() <= 0) {
            throw new InvalidPetDataException("Pet weight must be greater than zero");
        }
    }

}
