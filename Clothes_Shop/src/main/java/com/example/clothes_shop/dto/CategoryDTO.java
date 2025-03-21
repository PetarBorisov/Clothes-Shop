package com.example.clothes_shop.dto;

import java.util.List;

public class CategoryDTO {

    private Long id;
    private String name;
    private boolean isActive;
    private List<ProductDTO> products;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name, boolean isActive, List<ProductDTO> productDTOList) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.products = productDTOList;
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
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
