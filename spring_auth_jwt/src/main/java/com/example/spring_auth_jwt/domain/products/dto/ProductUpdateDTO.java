package com.example.spring_auth_jwt.domain.products.dto;

import java.math.BigDecimal;

public record ProductUpdateDTO(
        String name,
        String description,
        BigDecimal price
) {
}