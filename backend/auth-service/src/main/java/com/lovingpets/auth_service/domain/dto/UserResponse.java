package com.lovingpets.auth_service.domain.dto;

import java.util.List;

public record UserResponse(
        Long id,
        String email,
        boolean enabled,
        List<String> roles
) {
}
