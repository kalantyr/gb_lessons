package ru.geekbrains.spring.first.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.first.app.model.Order;
import ru.geekbrains.spring.first.app.repositories.OrdersRepository;

import java.util.List;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Order> findAllOrders() {
        return ordersRepository.findAll();
    }

    public void save(Order order) {
        ordersRepository.save(order);
    }
}
