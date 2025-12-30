package com.lovingpets.pet_service.domain.dto;

import com.lovingpets.pet_service.domain.model.Breed;
import com.lovingpets.pet_service.domain.model.Species;
import jakarta.validation.constraints.Positive;

public record PetUpdateDto(
        String name,
        Species species,
        Breed breed,
        @Positive(message = "Age must be greater than 0")
        Integer age,
        @Positive(message = "Weight must be greater than 0")
        Double weight
) {
}
