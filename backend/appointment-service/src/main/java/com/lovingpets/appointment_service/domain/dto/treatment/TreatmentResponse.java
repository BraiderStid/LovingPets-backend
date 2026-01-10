package com.lovingpets.appointment_service.domain.dto.treatment;

import com.lovingpets.appointment_service.domain.model.TreatmentStatus;

import java.time.LocalDate;

public record TreatmentResponse(
        Long id,
        String description,
        LocalDate startDate,
        LocalDate nextReviewDate,
        TreatmentStatus status
) {}