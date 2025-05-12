package com.example.spring_auth_jwt.domain.users.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
    @NotBlank String username,
    @NotBlank String password
) {
}
