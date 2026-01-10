package com.lovingpets.appointment_service.domain.exception.medicalRecord;

public class MedicalRecordNotFoundException extends RuntimeException {

    public MedicalRecordNotFoundException(String message) {
        super(message);
    }

    public MedicalRecordNotFoundException(Long id) {
        super("Medical record not found with id " + id);
    }
}