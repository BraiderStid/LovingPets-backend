package com.lovingpets.appointment_service.persistence.mapper;

import com.lovingpets.appointment_service.domain.dto.appointment.AppointmentResponse;
import com.lovingpets.appointment_service.persistence.entity.AppointmentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    AppointmentResponse toResponse(AppointmentEntity entity);

    List<AppointmentResponse> toResponseList(List<AppointmentEntity> entities);

}
