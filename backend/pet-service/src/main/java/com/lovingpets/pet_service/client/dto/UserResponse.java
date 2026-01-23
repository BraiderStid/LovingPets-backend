package com.lovingpets.pet_service.client.dto;

import java.util.List;

public record UserResponse(
        Long id,
        String email,
        boolean enabled,
        List<String> roles
) {}
