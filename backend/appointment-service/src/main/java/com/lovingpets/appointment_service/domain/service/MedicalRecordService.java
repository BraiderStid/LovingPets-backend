package com.lovingpets.appointment_service.domain.service;

import com.lovingpets.appointment_service.domain.dto.medicalRecord.MedicalRecordResponse;
import com.lovingpets.appointment_service.persistence.mapper.MedicalRecordMapper;
import com.lovingpets.appointment_service.persistence.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordMapper medicalRecordMapper;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, MedicalRecordMapper medicalRecordMapper) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.medicalRecordMapper = medicalRecordMapper;
    }

    public List<MedicalRecordResponse> findAll() {
        return medicalRecordMapper.toResponseList(medicalRecordRepository.findAll());
    }

}
