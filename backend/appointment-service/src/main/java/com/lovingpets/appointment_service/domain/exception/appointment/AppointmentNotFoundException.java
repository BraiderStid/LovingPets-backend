package com.lovingpets.appointment_service.domain.exception.appointment;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException(Long id) {
        super("Appointment not found with id " + id);
    }
}