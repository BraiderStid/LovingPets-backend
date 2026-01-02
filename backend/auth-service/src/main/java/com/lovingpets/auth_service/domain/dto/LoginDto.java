package com.lovingpets.auth_service.domain.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
