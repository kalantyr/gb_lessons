package ru.kalantyr.lesson11.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kalantyr.lesson11.dto.OrderDto;
import ru.kalantyr.lesson11.dto.OrderItemDto;
import ru.kalantyr.lesson11.dto.UserDto;
import ru.kalantyr.lesson11.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * Создать новый заказ
     * @param userId для какого пользователя
     */
    @PutMapping("/create")
    public OrderDto createOrder(@PathVariable Long userId) {
        return orderService.createOrder(userId);
    }

    /**
     * Добавить товар в заказ
     * @param orderId номер заказа
     * @param itemId какой товар одбавить
     * @param count сколько товара добавить
     */
    @PutMapping("/buy")
    public void buy(@PathVariable Long orderId, @PathVariable Long itemId, @PathVariable Integer count){
        orderService.addToOrder(orderId, itemId, count);
    }

    /**
     * Возвращает заказ по ID
     */
    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    /**
     * Возвращает все заказы
     */
    @GetMapping("/all")
    public List<OrderDto> getAll() {
        return orderService.getAll(null);
    }

    /**
     * Возвращает все заказы пользователя
     */
    @GetMapping("/byUser/{userId}")
    public List<OrderDto> getByUser(@PathVariable Long userId) {
        return orderService.getAll(userId);
    }

    /**
     * Возвращает пользователей, купивших указанный товар
     */
    @GetMapping("/byItem/{itemId}")
    public List<UserDto> getByItem(@PathVariable Long itemId) {
        return orderService.getUsersByItem(itemId);
    }
}
