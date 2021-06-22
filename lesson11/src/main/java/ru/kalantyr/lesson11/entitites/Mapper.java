package ru.kalantyr.lesson11.entitites;

import ru.kalantyr.lesson11.dto.*;

import java.util.stream.Collectors;

// наверно это можно сделать как-нибудь поизящнее (каким-нибудь автомаппером)
public class Mapper {

    public ItemDto map(Item item) {
        var itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setPrice(item.getPrice());
        itemDto.setTitle(item.getTitle());
        return itemDto;
    }

    public Item map(ItemDto itemDto) {
        var item = new Item();
        item.setId(itemDto.getId());
        item.setPrice(itemDto.getPrice());
        item.setTitle(itemDto.getTitle());
        return item;
    }

    public OrderItem map(OrderItemDto orderItemDto) {
        var orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setOrder(map(orderItemDto.getOrder()));
        orderItem.setItemId(orderItemDto.getItemId());
        orderItem.setCount(orderItemDto.getCount());
        orderItem.setPrice(orderItemDto.getPrice());
        return orderItem;
    }

    public OrderItemDto map(OrderItem orderItem) {
        var orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setOrder(map(orderItem.getOrder()));
        orderItemDto.setItemId(orderItem.getItemId());
        orderItemDto.setCount(orderItem.getCount());
        orderItemDto.setPrice(orderItem.getPrice());
        return orderItemDto;
    }

    public Order map(OrderDto orderDto) {
        if (orderDto ==null)
            return null;

        var order = new Order();
        order.setId(orderDto.getId());
        order.setUserId(orderDto.getUserId());
        order.setItems(orderDto.getItems().stream().map(this::map).collect(Collectors.toUnmodifiableSet()));
        return order;
    }

    public OrderDto map(Order order) {
        var orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserId(order.getUserId());
        orderDto.setItems(order.getItems().stream().map(this::map).collect(Collectors.toUnmodifiableList()));
        return orderDto;
    }

    public UserDto map(User user){
        var userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        return userDto;
    }

    public User map(UserDto userDto){
        var user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        return user;
    }
}
