package com.lovingpets.appointment_service.persistence.mapper;

import com.lovingpets.appointment_service.domain.dto.treatment.TreatmentRequest;
import com.lovingpets.appointment_service.domain.dto.treatment.TreatmentResponse;
import com.lovingpets.appointment_service.persistence.entity.TreatmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreatmentMapper {

    TreatmentResponse toResponse(TreatmentEntity entity);

    List<TreatmentResponse> toResponseList(List<TreatmentEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "medicalRecord", ignore = true)
    @Mapping(target = "status", ignore = true)
    TreatmentEntity toEntity(TreatmentRequest request);
}