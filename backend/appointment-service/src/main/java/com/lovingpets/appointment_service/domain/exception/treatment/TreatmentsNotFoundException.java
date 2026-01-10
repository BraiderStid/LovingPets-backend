package com.lovingpets.appointment_service.domain.exception.treatment;

import java.time.LocalDate;

public class TreatmentsNotFoundException extends RuntimeException {
    public TreatmentsNotFoundException(Long medicalRecordId, LocalDate date) {
        super("No treatments found"
                + (medicalRecordId != null ? " for medical record id " + medicalRecordId : "")
                + (date != null ? " on date " + date : "")
        );
    }
}