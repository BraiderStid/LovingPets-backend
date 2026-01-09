package com.lovingpets.appointment_service.domain.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message
) {}