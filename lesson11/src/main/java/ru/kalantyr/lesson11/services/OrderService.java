package ru.kalantyr.lesson11.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kalantyr.lesson11.dto.OrderDto;
import ru.kalantyr.lesson11.dto.OrderItemDto;
import ru.kalantyr.lesson11.entitites.Mapper;
import ru.kalantyr.lesson11.entitites.Order;
import ru.kalantyr.lesson11.entitites.OrderItem;
import ru.kalantyr.lesson11.repositories.ItemRepository;
import ru.kalantyr.lesson11.repositories.OrderItemRepository;
import ru.kalantyr.lesson11.repositories.OrderRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;
    private final ItemService itemService;
    private final Mapper mapper = new Mapper();

    @PostConstruct
    @Transactional
    public void init() {
        // Доостаём первого попавшегося пользователя
        var user = userService.getAll().get(0);

        var items = itemService.getAll();

        var orderDto = CreateOrder(user.getId());
        for (var item : items)
            addToOrder(orderDto.getId(), item.getId(), 3);
    }

    /**
     * Создаёт новый заказ
     * @return
     */
    public OrderDto CreateOrder(Long userId) {
        var orderDto = new OrderDto();
        orderDto.setUserId(userId);
        var order = orderRepository.save(mapper.map(orderDto));
        return mapper.map(order);
    }

    /**
     * Добавляет товар в заказ
     * @param orderId В какой заказ добавить
     * @param itemId Какой товар добавить
     * @param count Скорлько товаров этого вида добавить
     */
    @Transactional
    public OrderItemDto addToOrder(Long orderId, Long itemId, Integer count) {
        var order = orderRepository.findById(orderId).orElseThrow();
        var item = itemRepository.findById(itemId);

        var orderItem = new OrderItem();
        orderItem.setItemId(itemId);
        orderItem.setPrice(item.orElseThrow().getPrice()); // берём цену на данный момент, фиксируем
        orderItem.setCount(count);
        order.add(orderItem);
        orderRepository.save(order);
//        orderItemRepository.save(orderItem);

/*
        var order = orderRepository.findById(orderId);
//        var orderDto = mapper.map(order.orElseThrow());

        var item = itemRepository.findById(itemId);
        var itemDto = mapper.map(item.orElseThrow());

        var orderItemDto = new OrderItemDto();
//        orderItemDto.setOrder(orderDto);
        orderItemDto.setItemId(itemId);
        orderItemDto.setPrice(itemDto.getPrice()); // берём цену на данный момент, фиксируем
        orderItemDto.setCount(count);

        OrderItem oi = mapper.map(orderItemDto);
        oi.setOrder(order.orElseThrow());
        var orderItem = orderItemRepository.save(oi);

//        var items = new ArrayList<>(order.orElseThrow().getItems());
//        items.add(mapper.Map(orderItemDto));
//        order.orElseThrow().setItems(new HashSet<>(items));
//        orderRepository.save(order.orElseThrow());
*/
        return null;
    }

    public OrderDto getById(Long orderId) {
        var order = orderRepository.findById(orderId);
        return mapper.map(order.orElseThrow());
    }

    public List<OrderDto> getAll() {
        return orderRepository
                .findAll()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toUnmodifiableList());
    }
}
