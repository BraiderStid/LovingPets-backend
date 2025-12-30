package com.lovingpets.pet_service.domain.dto;

import com.lovingpets.pet_service.domain.model.Breed;
import com.lovingpets.pet_service.domain.model.Species;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PetDto(

        Long id,

        @NotNull(message = "Owner id is required")
        Long ownerId,

        @NotNull(message = "Pet name is required")
        String name,

        @NotNull(message = "Species is required")
        Species species,

        @NotNull(message = "Breed is required")
        Breed breed,

        @Positive(message = "Age must be greater than 0")
        Integer age,

        @Positive(message = "Weight must be greater than 0")
        Double weight
) {
}