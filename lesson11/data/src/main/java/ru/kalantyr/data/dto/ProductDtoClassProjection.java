package ru.kalantyr.data.dto;

import lombok.Data;

@Data
public class ProductDtoClassProjection {
    private String title;
    private int price;

    public ProductDtoClassProjection(String title, int price) {
        this.title = title;
        this.price = price;
    }
}
