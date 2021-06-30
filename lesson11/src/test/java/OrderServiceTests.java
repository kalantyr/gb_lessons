import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.kalantyr.lesson11.dto.*;
import ru.kalantyr.lesson11.exceptions.OrderNotFoundException;
import ru.kalantyr.lesson11.services.*;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@SpringBootTest
@ContextConfiguration(classes = ru.kalantyr.lesson11.Application.class)
public class OrderServiceTests {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Test
    public void addToOrderTest() {
        // создаём заказ и добавляем в него несуществующий товар
        var order = orderService.createOrder(123L);
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            orderService.addToOrder(order.getId(), 123L, 1);
        });
    }

    @Test
    public void getByIdTest() {
        // попытка получить несуществующий заказ
        var error = Assertions.assertThrows(OrderNotFoundException.class, () -> {
            orderService.getById(1_000L);
        });
        Assertions.assertEquals("Заказ не найден", error.getMessage());
    }

    @Test
    public void getUsersByItemTest() {
        // Глеб заказал кофе и мороженое, а Оксана - только мороженное

        UserDto gleb = new UserDto();
        gleb.setName("Глеб");
        gleb = userService.add(gleb);

        UserDto oksana = new UserDto();
        oksana.setName("Оксана");
        oksana = userService.add(oksana);

        ItemDto coffee = new ItemDto();
        coffee.setTitle("Кофе");
        coffee = itemService.add(coffee);

        ItemDto iceCream = new ItemDto();
        iceCream.setTitle("Мороженое");
        iceCream = itemService.add(iceCream);

        var order1 = orderService.createOrder(gleb.getId());
        orderService.addToOrder(order1.getId(), coffee.getId(), 1);
        orderService.addToOrder(order1.getId(), iceCream.getId(), 1);

        var order2 = orderService.createOrder(oksana.getId());
        orderService.addToOrder(order2.getId(), coffee.getId(), 1);

        // покупатели кофе
        var userIds = orderService.getUsersByItem(coffee.getId())
                .stream()
                .map(UserDto::getId)
                .collect(Collectors.toUnmodifiableList());
        Assertions.assertTrue(userIds.contains(gleb.getId()));
        Assertions.assertTrue(userIds.contains(oksana.getId()));

        // покупатели мороженного
        userIds = orderService.getUsersByItem(iceCream.getId())
                .stream()
                .map(UserDto::getId)
                .collect(Collectors.toUnmodifiableList());
        Assertions.assertTrue(userIds.contains(gleb.getId()));
        Assertions.assertFalse(userIds.contains(oksana.getId()));
    }
}
