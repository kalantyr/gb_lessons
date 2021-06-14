package ru.geekbrains.spring.first.app.repositories;

import ru.geekbrains.spring.first.app.model.Order;

import javax.persistence.*;

/**
 * Данные о заказе для храниения в БД
 */
@Entity
@Table(name = "orders")
public class OrderData {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OrderData() {
    }

    public OrderData(Order order) {
        this();
//        id = order.getId();
        title = order.getTitle();
    }

    public Order toOrder() {
        var order = new Order();
        order.setId(id);
        order.setTitle(title);
        return order;
    }
}
