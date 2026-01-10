package com.lovingpets.appointment_service.web.controller;

import com.lovingpets.appointment_service.domain.dto.medicalRecord.MedicalRecordRequest;
import com.lovingpets.appointment_service.domain.dto.medicalRecord.MedicalRecordResponse;
import com.lovingpets.appointment_service.domain.service.MedicalRecordService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecordResponse>> getMedicalRecords(
            @RequestParam(required = false) Long petId,
            @RequestParam(required = false) Long ownerId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {

        var responses = medicalRecordService.findMedicalRecords(petId, ownerId, date);

        return responses.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<MedicalRecordResponse> create(
            @Valid @RequestBody MedicalRecordRequest request
    ) {
        MedicalRecordResponse response =
                medicalRecordService.createMedicalRecord(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
