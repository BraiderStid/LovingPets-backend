package com.lovingpets.appointment_service.web.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.lovingpets.appointment_service.domain.dto.appointment.ErrorResponse;
import com.lovingpets.appointment_service.domain.exception.appointment.AppointmentConflictException;
import com.lovingpets.appointment_service.domain.exception.appointment.AppointmentNotFoundException;
import com.lovingpets.appointment_service.domain.exception.appointment.InvalidAppointmentStatusException;
import com.lovingpets.appointment_service.domain.exception.medicalRecord.MedicalRecordNotFoundException;
import com.lovingpets.appointment_service.domain.exception.treatment.InvalidTreatmentStatusException;
import com.lovingpets.appointment_service.domain.exception.treatment.MedicalRecordHasNoTreatmentsException;
import com.lovingpets.appointment_service.domain.exception.treatment.TreatmentNotFoundException;
import com.lovingpets.appointment_service.domain.exception.treatment.TreatmentsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAppointmentNotFound(AppointmentNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Appointment Not Found",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidAppointmentStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidStatus(InvalidAppointmentStatusException ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Appointment Status",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException invalidEnumEx) {
            String fieldName = invalidEnumEx.getPath().isEmpty() ? "unknown" : invalidEnumEx.getPath().get(0).getFieldName();
            String value = invalidEnumEx.getValue() == null ? "null" : invalidEnumEx.getValue().toString();

            ErrorResponse error = new ErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "Invalid Appointment Status",
                    "Invalid value for field '" + fieldName + "': " + value
            );

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Malformed JSON",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppointmentConflictException.class)
    public ResponseEntity<ErrorResponse> handleAppointmentConflict(AppointmentConflictException ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Appointment Conflict",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MedicalRecordNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMedicalRecordNotFound(
            MedicalRecordNotFoundException ex
    ) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Medical Record Not Found",
                ex.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MedicalRecordHasNoTreatmentsException.class)
    public ResponseEntity<ErrorResponse> handleNoTreatments(
            MedicalRecordHasNoTreatmentsException ex
    ) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Medical Record Has No Treatments",
                ex.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTreatmentStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTreatmentStatus(
            InvalidTreatmentStatusException ex
    ) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Treatment Status",
                ex.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TreatmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTreatmentNotFound(
            TreatmentNotFoundException ex
    ) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Treatment Not Found",
                ex.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TreatmentsNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTreatmentsNotFound(TreatmentsNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Treatments Not Found",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}