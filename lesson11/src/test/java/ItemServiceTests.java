import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kalantyr.lesson11.entitites.Item;
import ru.kalantyr.lesson11.repositories.ItemRepository;
import ru.kalantyr.lesson11.services.ItemService;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootTest(classes = ItemService.class)
public class ItemServiceTests {
    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    // Бессмысленный тест, так как сервис никакой логики не содержит, тупо маппинг из БД...
    @Test
    public void getAllTest() {
        ArrayList<Item> items = new ArrayList<>();

        Item i1 = new Item();
        i1.setId(1L);
        i1.setTitle("Кофе");
        i1.setPrice(BigDecimal.valueOf(123.45));
        items.add(i1);

        Mockito
                .doReturn(items)
                .when(itemRepository)
                .findAll();

        var allItems = itemService.getAll();
        Assertions.assertEquals(1, allItems.size());
        Assertions.assertEquals("Кофе", allItems.get(0).getTitle());
        Assertions.assertEquals(123.45, allItems.get(0).getPrice().doubleValue(), 0.001f);
    }
}
