package com.lovingpets.appointment_service.domain.dto;

import java.time.LocalDateTime;

public record AppointmentRequest(
        Long petId,
        Long ownerId,
        LocalDateTime appointmentDateTime,
        String notes
) {}
