package com.lovingpets.appointment_service.domain.dto.medicalRecord;

import java.time.LocalDateTime;

public record MedicalRecordResponse(
        Long id,
        Long appointmentId,
        Long employeeId,
        String diagnosis,
        LocalDateTime createdAt
) {}