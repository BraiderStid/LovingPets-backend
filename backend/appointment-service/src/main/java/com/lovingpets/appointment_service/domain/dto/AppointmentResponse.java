package com.lovingpets.appointment_service.domain.dto;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        Long petId,
        Long ownerId,
        LocalDateTime appointmentDateTime,
        String status,
        String notes
) {}