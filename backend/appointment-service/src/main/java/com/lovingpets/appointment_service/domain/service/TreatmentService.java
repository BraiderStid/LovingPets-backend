package com.lovingpets.appointment_service.domain.service;

import com.lovingpets.appointment_service.domain.dto.treatment.TreatmentRequest;
import com.lovingpets.appointment_service.domain.dto.treatment.TreatmentResponse;
import com.lovingpets.appointment_service.domain.exception.medicalRecord.MedicalRecordNotFoundException;
import com.lovingpets.appointment_service.domain.exception.treatment.MedicalRecordHasNoTreatmentsException;
import com.lovingpets.appointment_service.domain.exception.treatment.TreatmentNotFoundException;
import com.lovingpets.appointment_service.domain.exception.treatment.TreatmentsNotFoundException;
import com.lovingpets.appointment_service.persistence.entity.MedicalRecordEntity;
import com.lovingpets.appointment_service.persistence.entity.TreatmentEntity;
import com.lovingpets.appointment_service.persistence.mapper.TreatmentMapper;
import com.lovingpets.appointment_service.persistence.repository.MedicalRecordRepository;
import com.lovingpets.appointment_service.persistence.repository.TreatmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final TreatmentMapper treatmentMapper;

    public TreatmentService(
            TreatmentRepository treatmentRepository,
            MedicalRecordRepository medicalRecordRepository,
            TreatmentMapper treatmentMapper
    ) {
        this.treatmentRepository = treatmentRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.treatmentMapper = treatmentMapper;
    }

    public List<TreatmentResponse> findByMedicalRecord(Long medicalRecordId) {

        validateMedicalRecordExists(medicalRecordId);

        List<TreatmentEntity> entities =
                treatmentRepository.findByMedicalRecord_Id(medicalRecordId);

        validateMedicalRecordHasTreatments(entities, medicalRecordId);

        return treatmentMapper.toResponseList(entities);
    }

    private void validateMedicalRecordExists(Long medicalRecordId) {
        if (!medicalRecordRepository.existsById(medicalRecordId)) {
            throw new MedicalRecordNotFoundException(medicalRecordId);
        }
    }

    private void validateMedicalRecordHasTreatments(
            List<TreatmentEntity> entities,
            Long medicalRecordId
    ) {
        if (entities.isEmpty()) {
            throw new MedicalRecordHasNoTreatmentsException(medicalRecordId);
        }
    }

    public TreatmentResponse createTreatment(
            Long medicalRecordId,
            TreatmentRequest request
    ) {

        MedicalRecordEntity medicalRecord =
                medicalRecordRepository.findById(medicalRecordId)
                        .orElseThrow(() ->
                                new MedicalRecordNotFoundException(
                                        "Medical record with id " + medicalRecordId + " not found"
                                )
                        );

        TreatmentEntity treatment = treatmentMapper.toEntity(request);

        treatment.assignMedicalRecord(medicalRecord);

        TreatmentEntity saved = treatmentRepository.save(treatment);

        return treatmentMapper.toResponse(saved);
    }

    @Transactional
    public TreatmentResponse finishTreatment(Long treatmentId) {
        TreatmentEntity treatment = treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new TreatmentNotFoundException(treatmentId));

        treatment.finishTreatment();

        return treatmentMapper.toResponse(treatment);
    }

    public List<TreatmentResponse> findTreatments(Long treatmentId) {
        List<TreatmentEntity> entities;

        if (treatmentId != null) {
            entities = treatmentRepository.findById(treatmentId)
                    .map(List::of)
                    .orElseThrow(() -> new TreatmentsNotFoundException(treatmentId, null));
        } else {
            entities = treatmentRepository.findAll();
            if (entities.isEmpty()) {
                throw new TreatmentsNotFoundException(null, null);
            }
        }

        return treatmentMapper.toResponseList(entities);
    }

}