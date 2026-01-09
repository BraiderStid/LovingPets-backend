package com.lovingpets.appointment_service.persistence.mapper;

import com.lovingpets.appointment_service.domain.dto.medicalRecord.MedicalRecordResponse;
import com.lovingpets.appointment_service.persistence.entity.MedicalRecordEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {

    MedicalRecordResponse toResponse(MedicalRecordEntity entity);

    List<MedicalRecordResponse> toResponseList(List<MedicalRecordEntity> entities);
}