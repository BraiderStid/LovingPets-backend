package com.lovingpets.appointment_service.domain.exception.treatment;


public class MedicalRecordHasNoTreatmentsException extends RuntimeException {

    public MedicalRecordHasNoTreatmentsException(Long medicalRecordId) {
        super("Medical record with id " + medicalRecordId + " has no treatments yet");
    }
}