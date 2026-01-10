package com.lovingpets.appointment_service.domain.exception.treatment;

public class TreatmentNotFoundException extends RuntimeException {

    public TreatmentNotFoundException(Long id) {
        super("Treatment with id " + id + " not found");
    }
}