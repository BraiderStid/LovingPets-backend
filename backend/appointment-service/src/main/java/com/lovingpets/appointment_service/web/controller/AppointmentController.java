package com.lovingpets.appointment_service.web.controller;

import com.lovingpets.appointment_service.domain.dto.AppointmentResponse;
import com.lovingpets.appointment_service.domain.dto.UpdateAppointmentStatusRequest;
import com.lovingpets.appointment_service.domain.model.AppointmentStatus;
import com.lovingpets.appointment_service.domain.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<AppointmentResponse> getAppointments(
            @RequestParam(required = false) AppointmentStatus status,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {
        return appointmentService.findAppointments(status, date);
    }

    @PatchMapping("/{id}/status")
    public AppointmentResponse updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateAppointmentStatusRequest request
    ) {
        return appointmentService.updateStatus(id, request.status());
    }

}