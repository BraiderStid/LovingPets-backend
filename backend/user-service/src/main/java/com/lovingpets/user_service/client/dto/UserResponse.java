package com.lovingpets.user_service.client.dto;

import java.util.List;

public record UserResponse(
        Long id,
        String email,
        boolean enabled,
        List<String> roles
) {}
