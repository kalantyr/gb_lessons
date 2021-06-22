package ru.kalantyr.lesson11.entitites;

import ru.kalantyr.lesson11.dto.*;

import java.util.stream.Collectors;

// наверно это можно сделать как-нибудь поизящнее (каким-нибудь автомаппером)
public class Mapper {

    public ItemDto Map(Item item) {
        var itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setPrice(item.getPrice());
        itemDto.setTitle(item.getTitle());
        return itemDto;
    }

    public Item Map(ItemDto itemDto) {
        var item = new Item();
        item.setId(itemDto.getId());
        item.setPrice(itemDto.getPrice());
        item.setTitle(itemDto.getTitle());
        return item;
    }

    public OrderItem Map(OrderItemDto orderItemDto) {
        var orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setOrder(Map(orderItemDto.getOrder()));
        orderItem.setItemId(orderItemDto.getItemId());
        orderItem.setCount(orderItemDto.getCount());
        orderItem.setPrice(orderItemDto.getPrice());
        return orderItem;
    }

    public OrderItemDto Map(OrderItem orderItem) {
        var orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setOrder(Map(orderItem.getOrder()));
        orderItemDto.setItemId(orderItem.getItemId());
        orderItemDto.setCount(orderItem.getCount());
        orderItemDto.setPrice(orderItem.getPrice());
        return orderItemDto;
    }

    public Order Map(OrderDto orderDto) {
        var order = new Order();
        order.setId(orderDto.getId());
        order.setUserId(orderDto.getUserId());
        order.setItems(orderDto.getItems().stream().map(this::Map).collect(Collectors.toUnmodifiableSet()));
        return order;
    }

    public OrderDto Map(Order order) {
        var orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserId(order.getUserId());
        orderDto.setItems(order.getItems().stream().map(this::Map).collect(Collectors.toUnmodifiableList()));
        return orderDto;
    }
}
