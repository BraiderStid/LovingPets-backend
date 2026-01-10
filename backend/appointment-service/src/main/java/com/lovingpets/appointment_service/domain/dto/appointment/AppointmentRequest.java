package com.lovingpets.appointment_service.domain.dto.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AppointmentRequest(
        @Positive(message = "Id must be positive")
        Long petId,
        @Positive(message = "Id must be positive")
        Long ownerId,
        @Future(message = "The date and time must be in the future")
        LocalDateTime appointmentDateTime,
        @Size(max = 500, message = "Notes cannot exceed 500 characters")
        String notes
) {}
