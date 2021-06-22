package ru.kalantyr.lesson11.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kalantyr.lesson11.dto.ItemDto;
import ru.kalantyr.lesson11.dto.OrderDto;
import ru.kalantyr.lesson11.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PutMapping("/create")
    public OrderDto CreateOrder(@PathVariable Long userId) {
        return orderService.CreateOrder(userId);
    }

    @GetMapping("/all")
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }
}
