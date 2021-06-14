package ru.geekbrains.spring.first.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.first.app.model.Item;
import ru.geekbrains.spring.first.app.services.ItemService;
import ru.geekbrains.spring.first.app.utils.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/demo")
    public String demoKey() {
        return "Hello, World!";
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.findAll();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getItemById(@PathVariable Long id) {
//        Optional<Item> result = itemService.findById(id);
//        if (result.isPresent()) {
//            return new ResponseEntity<>(result.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Item with id: " + id + " not found"), HttpStatus.NOT_FOUND);
//    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item with id: " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item createNewItem(@RequestBody Item item) {
        return itemService.save(item);
    }
}
