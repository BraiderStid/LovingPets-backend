package com.lovingpets.user_service.domain.dto;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateUserRequest(

        @Size(max = 50, message = "First name must not exceed 50 characters")
        String firstName,

        @Size(max = 50, message = "Last name must not exceed 50 characters")
        String lastName,

        @Pattern(
                regexp = "^[0-9]{7,15}$",
                message = "Phone number must contain only digits (7–15)"
        )
        String phoneNumber,

        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth
) {
}