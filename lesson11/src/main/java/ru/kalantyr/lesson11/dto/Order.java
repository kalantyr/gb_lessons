package ru.kalantyr.lesson11.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kalantyr.lesson11.entitites.OrderItem;

import java.util.Set;

/**
 * Заказ
 */
@Data
@NoArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private Set<OrderItem> items;
}
