package com.lovingpets.appointment_service.domain.dto.appointment;

import com.lovingpets.appointment_service.domain.model.AppointmentStatus;
import org.jetbrains.annotations.NotNull;

public record UpdateAppointmentStatusRequest(
        @NotNull AppointmentStatus status
) {}