package com.example.spring_auth_jwt.domain.products.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import com.example.spring_auth_jwt.domain.products.Product;

public record ProductResponseDTO(
        UUID id, 
        String name,
        String description,
        BigDecimal price,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
    ) {
    public ProductResponseDTO(Product product){
        this(
            product.getId(), 
            product.getName(), 
            product.getDescription(),
            product.getPrice(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        );
    }
}