package ru.kalantyr.lesson11.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kalantyr.lesson11.dto.OrderDto;
import ru.kalantyr.lesson11.dto.UserDto;
import ru.kalantyr.lesson11.entitites.Mapper;
import ru.kalantyr.lesson11.entitites.Order;
import ru.kalantyr.lesson11.entitites.OrderItem;
import ru.kalantyr.lesson11.exceptions.OrderNotFoundException;
import ru.kalantyr.lesson11.repositories.ItemRepository;
import ru.kalantyr.lesson11.repositories.OrderRepository;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ItemService itemService;
    private final Mapper mapper = new Mapper();

    // для тестирования наполнить БД можно и через db.migration
    // но тогда нужно следить, чтобы структура таблиц и названия полей совпадали с объявленными в java (codeFirst vs dataFirst)
    @PostConstruct
    @Transactional
    public void init() {
        var rand = new Random();

        var items = itemService.getAll();

        for (var user : userService.getAll())
            for (var i = 0; i < 1 + rand.nextInt(5); i++) {
                var orderDto = createOrder(user.getId());
                for (var item : items)
                    if (rand.nextBoolean())
                        addToOrder(orderDto.getId(), item.getId(), 1 + rand.nextInt(10));
            }
    }

    /**
     * Создаёт новый заказ
     * @return
     */
    public OrderDto createOrder(Long userId) {
        var newOrder = new Order();
        newOrder.setUserId(userId);
        var order = orderRepository.save(newOrder);
        return mapper.map(order);
    }

    /**
     * Добавляет товар в заказ
     * @param orderId В какой заказ добавить
     * @param itemId Какой товар добавить
     * @param count Скорлько товаров этого вида добавить
     */
    @Transactional
    public void addToOrder(Long orderId, Long itemId, Integer count) {
        var order = orderRepository.findById(orderId).orElseThrow();
        var item = itemRepository.findById(itemId).orElseThrow();

        var orderItem = new OrderItem();
        orderItem.setItemId(itemId);
        orderItem.setPrice(item.getPrice()); // берём цену на данный момент, фиксируем
        orderItem.setCount(count);
        order.add(orderItem);

        // не совсем понятно - как получить ID для orderItem после сохранения?
        // искать orderItem в order.items по хэшу не получится, так как в заказе может быть несколько абсолютно одинаковых позиций
        orderRepository.save(order);
    }

    public OrderDto getById(Long orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Заказ не найден"));
        return mapper.map(order);
    }

    /**
     * Возвращает все заказы пользователя (если userId == null, то заказы всех пользователей)
     */
    public List<OrderDto> getAll(Long userId) {
        List<Order> orders;
        if (userId == null)
            orders = orderRepository.findAll();
        else
            orders = orderRepository.findAllByUserId(userId);

        return orders
                .stream()
                .map(mapper::map)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Возвращает всех пользователей, купивших указанный товар
     */
    public List<UserDto> getUsersByItem(long itemId) {
        var userIds = orderRepository.findUsersByItemIdForLesson(itemId);

        // почему в java так сложно с readonly-перечислениями :(
        var ids = userIds.stream().distinct().collect(Collectors.toUnmodifiableList());

        return userService.getById(ids);
    }
}
