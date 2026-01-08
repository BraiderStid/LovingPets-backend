package com.lovingpets.appointment_service.persistence.repository;

import com.lovingpets.appointment_service.persistence.entity.TreatmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<TreatmentEntity, Long> {
}
