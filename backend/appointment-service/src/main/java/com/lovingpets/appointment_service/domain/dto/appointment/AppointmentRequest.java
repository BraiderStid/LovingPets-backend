package com.lovingpets.appointment_service.domain.dto.appointment;

import java.time.LocalDateTime;

public record AppointmentRequest(
        Long petId,
        Long ownerId,
        LocalDateTime appointmentDateTime,
        String notes
) {}
