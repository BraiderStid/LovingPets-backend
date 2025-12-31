package com.lovingpets.auth_service.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @Email String email,
        @Size(min = 6) String password
) {}
