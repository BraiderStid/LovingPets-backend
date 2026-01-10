package com.lovingpets.appointment_service.domain.dto.treatment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TreatmentRequest(

        @NotBlank(message = "Description is required")
        @Size(max = 500, message = "Description cannot exceed 500 characters")
        String description,

        @Future(message = "Next review date must be in the future")
        LocalDate nextReviewDate
) {}