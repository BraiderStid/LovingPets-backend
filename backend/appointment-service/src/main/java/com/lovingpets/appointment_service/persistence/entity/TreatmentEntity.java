package com.lovingpets.appointment_service.persistence.entity;

import com.lovingpets.appointment_service.domain.model.TreatmentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "treatments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TreatmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medical_record_id", nullable = false)
    private MedicalRecordEntity medicalRecord;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate nextReviewDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TreatmentStatus status;

    public TreatmentEntity(String description, LocalDate startDate, LocalDate nextReviewDate) {
        this.description = description;
        this.startDate = startDate;
        this.nextReviewDate = nextReviewDate;
        this.status = TreatmentStatus.ACTIVE;
    }

    void assignMedicalRecord(MedicalRecordEntity medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public void finishTreatment() {
        this.status = TreatmentStatus.FINISHED;
    }
}
