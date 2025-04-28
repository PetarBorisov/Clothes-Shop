package com.example.clothes_shop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CategoryEditDTO {

    private Long id;

    @NotBlank
    private String name;
    private boolean isActive;
    private List<ProductAddDTO> products;

    public CategoryEditDTO() {
    }

    public CategoryEditDTO(Long id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public List<ProductAddDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductAddDTO> products) {
        this.products = products;
    }
}
