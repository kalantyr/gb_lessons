package ru.kalantyr.orm;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Repository<T> implements IRepository<T> {
    private final EntityManager entityManager;
    private final Class<T> dataClass;

    public Repository(EntityManagerFactory factory, Class<T> dataClass) {
        if (factory == null)
            throw new IllegalArgumentException();
        entityManager = factory.createEntityManager();
        this.dataClass = dataClass;
    }

    @Override
    public void add(T object) {
        if (object == null)
            throw new IllegalArgumentException();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public T get(long id) {
        // вроде можно использовать getReference(), если объект уже висит в памяти
        return entityManager.find(dataClass, id);
    }
}
