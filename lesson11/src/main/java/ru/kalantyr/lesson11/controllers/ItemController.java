package ru.kalantyr.lesson11.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kalantyr.lesson11.dto.ItemDto;
import ru.kalantyr.lesson11.services.ItemService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/all")
    public List<ItemDto> getAll() {
        return itemService.getAll();
    }

    @PutMapping("/addDemoData")
    public void addDemoItems() {
        ItemDto item1 = new ItemDto();
        item1.setTitle("Велоcипед");
        item1.setPrice(BigDecimal.valueOf(19_900.00));
        itemService.add(item1);

        ItemDto item2 = new ItemDto();
        item2.setTitle("Самокат");
        item2.setPrice(BigDecimal.valueOf(9_990.00));
        itemService.add(item2);

        ItemDto item3 = new ItemDto();
        item3.setTitle("Ролики");
        item3.setPrice(BigDecimal.valueOf(4_999.90));
        itemService.add(item3);
    }
}
