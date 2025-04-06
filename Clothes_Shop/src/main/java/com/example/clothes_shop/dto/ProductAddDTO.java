package com.example.clothes_shop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductAddDTO {

    private Long id;

    @NotBlank
    private String name;

    @Size(min = 3, max = 150)
    private String description;

    private String photo;

    @NotNull
    @Positive
    private Double price;

    @NotNull
    @Positive
    private Integer quantity;

    private CategoryAddDTO category;

    public ProductAddDTO() {
    }

    public ProductAddDTO(Long id, String name, String description,String photo, Double price, Integer quantity, CategoryAddDTO category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public CategoryAddDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryAddDTO category) {
        this.category = category;
    }
}
