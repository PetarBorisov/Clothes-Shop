package com.example.clothes_shop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CategoryAddDTO {


    private Long id;

    @NotBlank(message = "Category name cannot be empty")
    private String name;

    private boolean isActive;

    private List<ProductAddDTO> products;



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
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<ProductAddDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductAddDTO> products) {
        this.products = products;
    }
}
