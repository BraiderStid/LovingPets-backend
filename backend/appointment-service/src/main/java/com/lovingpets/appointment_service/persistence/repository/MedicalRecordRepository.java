package com.lovingpets.appointment_service.persistence.repository;

import com.lovingpets.appointment_service.persistence.entity.MedicalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, Long> {
}
