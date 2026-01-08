package com.lovingpets.appointment_service.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medical_records")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MedicalRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "appointment_id", nullable = false, unique = true)
    private AppointmentEntity appointment;

    @Column(nullable = false)
    private Long employeeId;

    @Column(nullable = false, length = 1000)
    private String diagnosis;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "medicalRecord",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<TreatmentEntity> treatments = new ArrayList<>();

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public MedicalRecordEntity(AppointmentEntity appointment, Long employeeId, String diagnosis) {
        this.appointment = appointment;
        this.employeeId = employeeId;
        this.diagnosis = diagnosis;
    }

    public void addTreatment(TreatmentEntity treatment) {
        treatments.add(treatment);
        treatment.assignMedicalRecord(this);
    }
}