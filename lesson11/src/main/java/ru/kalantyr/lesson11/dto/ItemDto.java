package ru.kalantyr.lesson11.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * Товар в магазине
 */
@Data
@NoArgsConstructor
public class ItemDto {
    private Long id;

    private BigDecimal price;

    private String title;
}
