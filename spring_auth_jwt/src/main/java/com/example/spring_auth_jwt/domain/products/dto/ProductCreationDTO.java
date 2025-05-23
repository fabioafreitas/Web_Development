package com.example.spring_auth_jwt.domain.products.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreationDTO(
        @NotBlank String name,
        String description,
        @NotNull BigDecimal price
) {
}