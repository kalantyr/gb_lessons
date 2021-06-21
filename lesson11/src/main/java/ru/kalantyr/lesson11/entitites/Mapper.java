package ru.kalantyr.lesson11.entitites;

import ru.kalantyr.lesson11.dto.ItemDto;

public class Mapper {

    public ItemDto Convert(Item item) {
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setPrice(item.getPrice());
        dto.setTitle(item.getTitle());
        return dto;
    }

    public Item Convert(ItemDto itemDto) {
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setPrice(itemDto.getPrice());
        item.setTitle(itemDto.getTitle());
        return item;
    }
}
