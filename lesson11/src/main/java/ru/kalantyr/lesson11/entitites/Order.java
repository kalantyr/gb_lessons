package ru.kalantyr.lesson11.entitites;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

/**
 * Заказ
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
@NamedEntityGraph(
        name = "Order.with-items",
        attributeNodes = {
                @NamedAttributeNode("items")
        }
)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items;
}
