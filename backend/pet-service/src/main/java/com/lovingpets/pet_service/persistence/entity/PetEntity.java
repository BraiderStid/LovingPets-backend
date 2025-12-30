package com.lovingpets.pet_service.persistence.entity;
import com.lovingpets.pet_service.domain.model.Species;
import com.lovingpets.pet_service.domain.model.Breed;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false, length = 40)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Species species;

    @Enumerated(EnumType.STRING)
    private Breed breed;

    private Integer age;

    private Double weight;

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDate.now();
    }
}