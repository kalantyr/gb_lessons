package ru.geekbrains.spring.first.app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.first.app.model.Item;
import ru.geekbrains.spring.first.app.repositories.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public Item add(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        checkItem(item);

        return itemRepository.save(item);
    }

    public void update(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        checkItem(item);

        itemRepository.save(item);
    }

    public void delete(long id) {
        itemRepository.deleteById(id);
    }

    private void checkItem(Item item) {
        // для более сложных проверок можно отдельный класс-валидатор сделать

        if (item.getPrice() <= 0)
            throw new RuntimeException("Цена должна быть положительной");
    }
}
