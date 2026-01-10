package com.lovingpets.appointment_service.persistence.repository;

import com.lovingpets.appointment_service.persistence.entity.MedicalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, Long> {

    List<MedicalRecordEntity> findByCreatedAtBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    List<MedicalRecordEntity> findByAppointment_PetId(Long petId);

    List<MedicalRecordEntity> findByAppointment_OwnerId(Long ownerId);

    List<MedicalRecordEntity> findByAppointment_PetIdAndAppointment_OwnerId(
            Long petId,
            Long ownerId
    );

    List<MedicalRecordEntity> findByAppointment_PetIdAndCreatedAtBetween(
            Long petId,
            LocalDateTime start,
            LocalDateTime end
    );

    boolean existsByAppointment_Id(Long appointmentId);

}
