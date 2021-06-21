package ru.kalantyr.lesson11.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kalantyr.lesson11.dto.ItemDto;
import ru.kalantyr.lesson11.entitites.Mapper;
import ru.kalantyr.lesson11.repositories.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    private final Mapper mapper = new Mapper();

    public void add(ItemDto itemDto)
    {
        itemRepository.save(mapper.Convert(itemDto));
    }

    public List<ItemDto> getAll() {
        return itemRepository
                .findAll()
                .stream()
                .map(mapper::Convert)
                .collect(Collectors.toUnmodifiableList());
    }
}
