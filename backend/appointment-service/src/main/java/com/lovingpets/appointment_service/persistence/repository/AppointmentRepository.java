package com.lovingpets.appointment_service.persistence.repository;

import com.lovingpets.appointment_service.domain.model.AppointmentStatus;
import com.lovingpets.appointment_service.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    List<AppointmentEntity> findByStatus(AppointmentStatus status);

    @Query("""
        SELECT a FROM AppointmentEntity a
        WHERE DATE(a.appointmentDateTime) = :date
    """)
    List<AppointmentEntity> findByDate(LocalDate date);

    @Query("""
        SELECT a FROM AppointmentEntity a
        WHERE a.status = :status
          AND DATE(a.appointmentDateTime) = :date
    """)
    List<AppointmentEntity> findByStatusAndDate(
            AppointmentStatus status,
            LocalDate date
    );

    Optional<AppointmentEntity> findByAppointmentDateTimeAndStatusNot(
            LocalDateTime dateTime, AppointmentStatus status
    );

}
