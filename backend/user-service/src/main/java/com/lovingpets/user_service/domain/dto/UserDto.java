package com.lovingpets.user_service.domain.dto;

import java.time.LocalDate;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        LocalDate dateOfBirth
) {
}