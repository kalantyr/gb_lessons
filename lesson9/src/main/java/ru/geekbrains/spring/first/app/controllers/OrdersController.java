package ru.geekbrains.spring.first.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.first.app.model.Order;
import ru.geekbrains.spring.first.app.services.OrdersService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    /**
     * Получение заказа по его ID
     */
    @GetMapping("/{id}")
    public Order getById(@PathVariable long id) {
        return ordersService.getById(id);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return ordersService.findAllOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createNewOrder(@RequestBody Order order) {
        ordersService.save(order);
        return order;
    }
}
