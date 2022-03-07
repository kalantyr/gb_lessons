package ru.geekbrains.spring.first.app;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ru.geekbrains.spring.first.app.model.Item;
import ru.geekbrains.spring.first.app.repositories.ItemRepository;
import ru.geekbrains.spring.first.app.services.ItemService;

public class ItemServiceTests {
    @Test
    public void checkPriceTest() {
        var repository = Mockito.mock(ItemRepository.class);
        var service = new ItemService(repository);

        var item = new Item();

        for (var p: new int[] { -100, -1, 0 }) // некорректные значения
        {
            item.setPrice(p);
            Assert.assertThrows(RuntimeException.class, () -> {
                service.add(item);
            });
        }

        for (var p: new int[] { 1, 10, 100 }) // корректные значения
        {
            item.setPrice(p);
            service.add(item);
        }
    }
}
