package ru.kalantyr.lesson11.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kalantyr.lesson11.dto.ItemDto;
import ru.kalantyr.lesson11.entitites.Mapper;
import ru.kalantyr.lesson11.repositories.ItemRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final Mapper mapper = new Mapper();

    @PostConstruct
    public void init() {
        ItemDto item1 = new ItemDto();
        item1.setTitle("Велоcипед");
        item1.setPrice(BigDecimal.valueOf(19_900.00));
        add(item1);

        ItemDto item2 = new ItemDto();
        item2.setTitle("Самокат");
        item2.setPrice(BigDecimal.valueOf(9_990.00));
        add(item2);

        ItemDto item3 = new ItemDto();
        item3.setTitle("Ролики");
        item3.setPrice(BigDecimal.valueOf(4_999.90));
        add(item3);
    }

    public void add(ItemDto itemDto)
    {
        itemRepository.save(mapper.Map(itemDto));
    }

    public List<ItemDto> getAll() {
        return itemRepository
                .findAll()
                .stream()
                .map(mapper::Map)
                .collect(Collectors.toUnmodifiableList());
    }
}
