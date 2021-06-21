package ru.kalantyr.lesson11.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Позиция в заказе
 */
@Data
@NoArgsConstructor
public class OrderItem {
    private Long id;

    private Long itemId;

    /**
     * Фиксированная стоимость единицы товара
     */
    private BigDecimal price;

    private Integer count;
}
