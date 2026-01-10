package com.lovingpets.appointment_service.web.controller;

import com.lovingpets.appointment_service.domain.dto.treatment.TreatmentResponse;
import com.lovingpets.appointment_service.domain.service.TreatmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treatments")
public class TreatmentController {

    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping
    public ResponseEntity<List<TreatmentResponse>> getTreatments(
            @RequestParam(required = false) Long treatmentId
    ) {
        return ResponseEntity.ok(treatmentService.findTreatments(treatmentId));
    }

    @PatchMapping("/{id}/finish")
    public ResponseEntity<TreatmentResponse> finishTreatment(
            @PathVariable Long id
    ) {
        TreatmentResponse response = treatmentService.finishTreatment(id);
        return ResponseEntity.ok(response);
    }
}