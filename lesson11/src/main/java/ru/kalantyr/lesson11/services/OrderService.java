package ru.kalantyr.lesson11.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kalantyr.lesson11.dto.OrderDto;
import ru.kalantyr.lesson11.dto.OrderItemDto;
import ru.kalantyr.lesson11.entitites.Mapper;
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
    private final Mapper mapper = new Mapper();

    @PostConstruct
    public void init() {
        var orderDto = CreateOrder(123L);
        addToOrder(orderDto.getId(), 1L, 1);
        addToOrder(orderDto.getId(), 2L, 2);
        addToOrder(orderDto.getId(), 3L, 3);
    }

    /**
     * Создаёт новый заказ
     * @return
     */
    public OrderDto CreateOrder(Long userId) {
        var orderDto = new OrderDto();
        orderDto.setUserId(userId);
        var order = orderRepository.save(mapper.Map(orderDto));
        return mapper.Map(order);
    }

    /**
     * Добавляет товар в заказ
     * @param orderId В какой заказ добавить
     * @param itemId Какой товар добавить
     * @param count Скорлько товаров этого вида добавить
     */
    @Transactional
    public OrderItemDto addToOrder(Long orderId, Long itemId, Integer count) {
        var order = orderRepository.findById(orderId);
        var orderDto = mapper.Map(order.orElseThrow());

        var item = itemRepository.findById(itemId);
        var itemDto = mapper.Map(item.orElseThrow());

        var orderItemDto = new OrderItemDto();
        orderItemDto.setOrder(orderDto);
        orderItemDto.setItemId(itemId);
        orderItemDto.setPrice(itemDto.getPrice()); // берём цену на данный момент, фиксируем
        orderItemDto.setCount(count);
        var orderItem = orderItemRepository.save(mapper.Map(orderItemDto));

        return mapper.Map(orderItem);
    }

    public List<OrderDto> getAll() {
        return orderRepository
                .findAll()
                .stream()
                .map(mapper::Map)
                .collect(Collectors.toUnmodifiableList());
    }
}
