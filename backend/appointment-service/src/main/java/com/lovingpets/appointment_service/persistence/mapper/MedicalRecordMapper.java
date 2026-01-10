package com.lovingpets.appointment_service.persistence.mapper;

import com.lovingpets.appointment_service.domain.dto.medicalRecord.MedicalRecordResponse;
import com.lovingpets.appointment_service.persistence.entity.MedicalRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {

    @Mapping(source = "appointment.id", target = "appointmentId")
    @Mapping(target = "petId", source = "appointment.petId")
    @Mapping(source = "appointment.ownerId", target = "ownerId")
    MedicalRecordResponse toResponse(MedicalRecordEntity entity);

    List<MedicalRecordResponse> toResponseList(List<MedicalRecordEntity> entities);
}