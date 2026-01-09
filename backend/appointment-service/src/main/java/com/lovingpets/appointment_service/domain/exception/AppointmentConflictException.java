package com.lovingpets.appointment_service.domain.exception;

public class AppointmentConflictException extends RuntimeException {

    public AppointmentConflictException(String message) {
        super(message);
    }

    public AppointmentConflictException() {
        super("There is already an active appointment at this date and time.");
    }
}