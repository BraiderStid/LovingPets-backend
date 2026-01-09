package com.lovingpets.appointment_service.domain.service;

import com.lovingpets.appointment_service.domain.dto.AppointmentResponse;
import com.lovingpets.appointment_service.domain.exception.AppointmentNotFoundException;
import com.lovingpets.appointment_service.domain.exception.InvalidAppointmentStatusException;
import com.lovingpets.appointment_service.domain.model.AppointmentStatus;
import com.lovingpets.appointment_service.persistence.entity.AppointmentEntity;
import com.lovingpets.appointment_service.persistence.mapper.AppointmentMapper;
import com.lovingpets.appointment_service.persistence.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }


    public List<AppointmentResponse> findAppointments(
            AppointmentStatus status,
            LocalDate date
    ) {

        List<AppointmentEntity> entities;

        if (status != null && date != null) {
            entities = appointmentRepository.findByStatusAndDate(status, date);
        } else if (status != null) {
            entities = appointmentRepository.findByStatus(status);
        } else if (date != null) {
            entities = appointmentRepository.findByDate(date);
        } else {
            entities = appointmentRepository.findAll();
        }

        return appointmentMapper.toResponseList(entities);
    }

    @Transactional
    public AppointmentResponse updateStatus(Long id, AppointmentStatus newStatus) {
        AppointmentEntity appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));

        applyStatusChange(appointment, newStatus);

        return appointmentMapper.toResponse(appointment);
    }

    private void applyStatusChange(AppointmentEntity appointment, AppointmentStatus status) {
        if (status == null) {
            throw new InvalidAppointmentStatusException("Appointment status cannot be null");
        }

        switch (status) {
            case ATTENDED -> appointment.markAsAttended();
            case CANCELLED -> appointment.cancel();
            case PENDING -> appointment.markAsPending();
            default -> throw new InvalidAppointmentStatusException(
                    "Invalid appointment status: " + status
            );
        }
    }

}
