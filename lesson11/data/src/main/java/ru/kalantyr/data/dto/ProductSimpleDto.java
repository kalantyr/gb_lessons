package ru.kalantyr.data.dto;

import ru.kalantyr.data.entitites.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductSimpleDto {
    private Long id;
    private String title;
    private int anotherPriceField;

    public ProductSimpleDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.anotherPriceField = product.getPrice();
    }

    public ProductSimpleDto(Long id, String title, int anotherPriceField) {
        this.id = id;
        this.title = title;
        this.anotherPriceField = anotherPriceField;
    }
}
