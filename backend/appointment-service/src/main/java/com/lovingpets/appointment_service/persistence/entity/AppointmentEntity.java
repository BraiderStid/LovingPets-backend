package com.lovingpets.appointment_service.persistence.entity;

import com.lovingpets.appointment_service.domain.model.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long petId;

    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false)
    private LocalDateTime appointmentDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    @Column(length = 500)
    private String notes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "appointment", fetch = FetchType.LAZY)
    private MedicalRecordEntity medicalRecord;

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = AppointmentStatus.PENDING;
    }

    public AppointmentEntity(Long petId, Long ownerId, LocalDateTime appointmentDateTime, String notes) {
        this.petId = petId;
        this.ownerId = ownerId;
        this.appointmentDateTime = appointmentDateTime;
        this.notes = notes;
        this.status = AppointmentStatus.PENDING;
    }

    public void markAsAttended() {
        this.status = AppointmentStatus.ATTENDED;
    }

    public void markAsPending() {
        this.status = AppointmentStatus.PENDING;
    }

    public void cancel() {
        this.status = AppointmentStatus.CANCELLED;
    }

    public void updatePetId(Long petId) {
        if (petId != null) {
            this.petId = petId;
        }
    }

    public void updateOwnerId(Long ownerId) {
        if (ownerId != null) {
            this.ownerId = ownerId;
        }
    }

    public void updateNotes(String notes) {
        this.notes = notes;
    }

    public void updateAppointmentDateTime(LocalDateTime newDateTime) {
        this.appointmentDateTime = newDateTime;
    }
}