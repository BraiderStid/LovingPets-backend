package com.lovingpets.appointment_service.domain.service;

import com.lovingpets.appointment_service.domain.dto.medicalRecord.MedicalRecordRequest;
import com.lovingpets.appointment_service.domain.dto.medicalRecord.MedicalRecordResponse;
import com.lovingpets.appointment_service.domain.exception.appointment.AppointmentConflictException;
import com.lovingpets.appointment_service.domain.exception.appointment.AppointmentNotFoundException;
import com.lovingpets.appointment_service.domain.exception.medicalRecord.MedicalRecordNotFoundException;
import com.lovingpets.appointment_service.persistence.entity.AppointmentEntity;
import com.lovingpets.appointment_service.persistence.entity.MedicalRecordEntity;
import com.lovingpets.appointment_service.persistence.mapper.MedicalRecordMapper;
import com.lovingpets.appointment_service.persistence.repository.AppointmentRepository;
import com.lovingpets.appointment_service.persistence.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordMapper medicalRecordMapper;
    private final AppointmentRepository appointmentRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, MedicalRecordMapper medicalRecordMapper, AppointmentRepository appointmentRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.medicalRecordMapper = medicalRecordMapper;
        this.appointmentRepository = appointmentRepository;
    }

    public List<MedicalRecordResponse> findMedicalRecords(
            Long petId,
            Long ownerId,
            LocalDate date
    ) {

        List<MedicalRecordEntity> entities;

        if (petId != null && date != null) {

            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(23, 59, 59);

            entities = medicalRecordRepository
                    .findByAppointment_PetIdAndCreatedAtBetween(petId, start, end);

        } else if (petId != null && ownerId != null) {

            entities = medicalRecordRepository
                    .findByAppointment_PetIdAndAppointment_OwnerId(petId, ownerId);

        } else if (petId != null) {

            entities = medicalRecordRepository
                    .findByAppointment_PetId(petId);

        } else if (ownerId != null) {

            entities = medicalRecordRepository
                    .findByAppointment_OwnerId(ownerId);

        } else if (date != null) {

            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(23, 59, 59);

            entities = medicalRecordRepository
                    .findByCreatedAtBetween(start, end);

        } else {
            entities = medicalRecordRepository.findAll();
        }

        if (entities.isEmpty()) {
            throw new MedicalRecordNotFoundException(
                    "No medical records found with the given filters"
            );
        }

        return medicalRecordMapper.toResponseList(entities);
    }

    public MedicalRecordResponse createMedicalRecord(MedicalRecordRequest request) {

        AppointmentEntity appointment = appointmentRepository.findById(request.appointmentId())
                .orElseThrow(() ->
                        new AppointmentNotFoundException(request.appointmentId())
                );

        if (medicalRecordRepository.existsByAppointment_Id(appointment.getId())) {
            throw new AppointmentConflictException(
                    "Medical record already exists for appointment id " + appointment.getId()
            );
        }

        MedicalRecordEntity medicalRecord = new MedicalRecordEntity(
                appointment,
                request.employeeId(),
                request.diagnosis()
        );

        medicalRecordRepository.save(medicalRecord);

        return medicalRecordMapper.toResponse(medicalRecord);
    }

}
