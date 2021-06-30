import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import ru.kalantyr.lesson11.exceptions.OrderNotFoundException;
import ru.kalantyr.lesson11.repositories.*;
import ru.kalantyr.lesson11.services.*;
import java.util.NoSuchElementException;

@SpringBootTest
@ContextConfiguration(classes = ru.kalantyr.lesson11.Application.class)
public class OrderServiceTests {
    @Autowired
    private OrderService orderService;

//    @MockBean
//    private ItemRepository itemRepository;
//
//    @MockBean
//    private UserRepository userRepository;

    @Test
    public void addToOrderTest() {
        // создаём товар и добавляем в него несуществующий товар
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
}
