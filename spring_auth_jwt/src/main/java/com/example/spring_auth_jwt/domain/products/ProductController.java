package com.example.spring_auth_jwt.domain.products;

import org.springframework.web.bind.annotation.*;

import com.example.spring_auth_jwt.domain.products.dto.ProductCreationDTO;
import com.example.spring_auth_jwt.domain.products.dto.ProductResponseDTO;
import com.example.spring_auth_jwt.domain.products.dto.ProductUpdateDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductResponseDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable("id") UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public ProductResponseDTO create(@RequestBody ProductCreationDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO update(@PathVariable("id") UUID id, @RequestBody ProductUpdateDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }
}
