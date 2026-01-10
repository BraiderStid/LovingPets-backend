package com.lovingpets.appointment_service.web.controller;

import com.lovingpets.appointment_service.domain.dto.treatment.TreatmentRequest;
import com.lovingpets.appointment_service.domain.dto.treatment.TreatmentResponse;
import com.lovingpets.appointment_service.domain.service.TreatmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-records/{medicalRecordId}/treatments")
public class MedicalRecordTreatmentController {

    private final TreatmentService treatmentService;

    public MedicalRecordTreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping
    public ResponseEntity<List<TreatmentResponse>> getTreatments(
            @PathVariable Long medicalRecordId
    ) {
        List<TreatmentResponse> responses =
                treatmentService.findByMedicalRecord(medicalRecordId);

        return responses.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<TreatmentResponse> createTreatment(
            @PathVariable Long medicalRecordId,
            @Valid @RequestBody TreatmentRequest request
    ) {
        TreatmentResponse response =
                treatmentService.createTreatment(medicalRecordId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}