package com.lovingpets.appointment_service.domain.exception.treatment;

public class InvalidTreatmentStatusException extends RuntimeException {

    public InvalidTreatmentStatusException(String message) {
        super(message);
    }
}