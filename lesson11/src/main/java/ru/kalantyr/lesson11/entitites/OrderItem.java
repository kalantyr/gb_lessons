package ru.kalantyr.lesson11.entitites;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Позиция в заказе
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "orderItems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userId")
    private Long itemId;

    /**
     * Фиксированная стоимость единицы товара
     */
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "count")
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
