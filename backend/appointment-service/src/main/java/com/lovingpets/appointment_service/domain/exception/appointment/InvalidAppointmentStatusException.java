package com.lovingpets.appointment_service.domain.exception.appointment;

public class InvalidAppointmentStatusException extends RuntimeException {

    public InvalidAppointmentStatusException(String message) {
        super(message);
    }
}