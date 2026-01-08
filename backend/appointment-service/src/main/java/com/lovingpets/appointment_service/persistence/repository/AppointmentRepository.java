package com.lovingpets.appointment_service.persistence.repository;

import com.lovingpets.appointment_service.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
}
