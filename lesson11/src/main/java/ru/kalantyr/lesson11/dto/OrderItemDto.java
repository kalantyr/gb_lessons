package ru.kalantyr.lesson11.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Позиция в заказе
 */
@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long id;

    /**
     * Заказ, в который входит эта позиция
     */
    private OrderDto order;

    private Long itemId;

    /**
     * Фиксированная стоимость единицы товара
     */
    private BigDecimal price;

    private Integer count;
}
