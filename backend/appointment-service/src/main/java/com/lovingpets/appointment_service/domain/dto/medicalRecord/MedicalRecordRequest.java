package com.lovingpets.appointment_service.domain.dto.medicalRecord;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MedicalRecordRequest(
        @NotNull(message = "Id is required")
        @Positive(message = "Id must be positive")
        Long appointmentId,
        @Positive(message = "Id must be positive")
        @NotNull(message = "Id is required")
        Long employeeId,
        @NotBlank(message = "Diagnosis is required")
        @Size(max = 1000, message = "The diagnosis cannot exceed 1000 characters")
        String diagnosis
) {}