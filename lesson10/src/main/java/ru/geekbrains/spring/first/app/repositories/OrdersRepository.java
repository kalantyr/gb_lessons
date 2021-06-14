package ru.geekbrains.spring.first.app.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.first.app.model.Order;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class OrdersRepository {
    private List<Order> items;

    @PostConstruct
    public void init() {
        items = new ArrayList<>(Arrays.asList(
                new Order(1L, "Order #1"),
                new Order(2L, "Order #2"),
                new Order(3L, "Order #3")
        ));
    }

    public List<Order> findAll() {
        return Collections.unmodifiableList(items);
    }

    public void save(Order order) {
        items.add(order);
    }
}
