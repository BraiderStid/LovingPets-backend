package com.lovingpets.pet_service.domain.service;

import com.lovingpets.pet_service.client.AuthClient;
import com.lovingpets.pet_service.client.dto.UserResponse;
import com.lovingpets.pet_service.domain.dto.PetDto;
import com.lovingpets.pet_service.domain.dto.PetUpdateDto;
import com.lovingpets.pet_service.domain.exception.AccountNotFoundException;
import com.lovingpets.pet_service.domain.exception.InvalidPetDataException;
import com.lovingpets.pet_service.domain.exception.PetNotFoundException;
import com.lovingpets.pet_service.domain.repository.PetRepository;
import dev.langchain4j.agent.tool.Tool;
import feign.FeignException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetRepository petRepository;
    private final AuthClient authClient;

    public PetService(PetRepository petRepository, AuthClient authClient) {
        this.petRepository = petRepository;
        this.authClient = authClient;
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

    public List<PetDto> getMyPets() {
        Long userId = getCurrentUserId();
        return petRepository.getByOwnerId(userId);
    }

    private Long getCurrentUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Long userId)) {
            throw new RuntimeException("User not authenticated");
        }

        return userId;
    }

    public PetDto save(PetDto petDto) {
        validateOwnerExists(petDto.ownerId());
        validatePetData(petDto);
        return petRepository.save(petDto);
    }

    public PetDto update(long id, PetUpdateDto petUpdateDto) {
        PetDto existing = this.petRepository.getById(id);
        if (existing == null) {
            throw new PetNotFoundException(id);
        }

        validatePetDataUpdate(petUpdateDto);
        return this.petRepository.update(id, petUpdateDto);
    }

    public void delete(long id) {
        PetDto existing = this.petRepository.getById(id);
        if (existing == null) {
            throw new PetNotFoundException(id);
        }
        this.petRepository.delete(id);
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

    private void validateOwnerExists(Long ownerId) {
        try {
            UserResponse user = authClient.getUserById(ownerId);

            if (user == null) {
                throw new AccountNotFoundException(ownerId);
            }

        } catch (FeignException.NotFound ex) {
            throw new AccountNotFoundException(ownerId);
        }
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
