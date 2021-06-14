package ru.geekbrains.spring.first.app.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.first.app.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
public class OrdersRepository {
    private EntityManagerFactory factory;

    public OrdersRepository(EntityManagerFactory factory) {
        if (factory == null)
            throw new IllegalArgumentException();
        this.factory = factory;
    }

    /**
     * Возвращает заказ по его ID
     */
    public Order getById(long id) {
        try{
            var data = getRecord(id);
            if (data == null)
                return null; // либо генерировать исключение NotFond
            return data.toOrder();
        }
        catch (Exception e) {
            throw new RuntimeException("Не удалось получить заказы из БД", e);
        }
    }

    /**
     * Возвращает все заказы
     */
    public List<Order> findAll() {
        try{
            return getAllRecords()
                    .stream()
                    .map(OrderData::toOrder)
                    .collect(Collectors.toUnmodifiableList());
        }
        catch (Exception e) {
            throw new RuntimeException("Не удалось получить заказы из БД", e);
        }
    }

    public void save(Order order) {
        try {
            if (order == null)
                throw new IllegalArgumentException();

            var data = new OrderData(order);

            withCommit(entityManager -> {
                entityManager.persist(data);
            });

            order.setId(data.getId()); // устанваливаем ID, выданный базой
        }
        catch (Exception e) {
            throw new RuntimeException("Не удалось сохранить заказ в БД", e);
        }
    }

    /**
     * достаёт записи из таблицы заказов
     */
    private OrderData getRecord(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            return entityManager.find(OrderData.class, id);
        }
        finally{
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * достаёт все записи из таблицы заказов
     */
    private List<OrderData> getAllRecords() {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();

            var q = "from OrderData";  // вот тут бы пригодилась конструкция nameof(OrderData)
            TypedQuery<OrderData> query = entityManager.createQuery(q, OrderData.class);
            return query.getResultList();
        }
        finally{
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Выполняет действие в рамках транзации
     */
    private void withCommit(Consumer<EntityManager> consumer) {
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();

            entityManager.getTransaction().begin();
            consumer.accept(entityManager);
            entityManager.getTransaction().commit();        }
        catch (Exception e) {
            if (entityManager != null)
                entityManager.getTransaction().rollback();
        }
        finally{
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
