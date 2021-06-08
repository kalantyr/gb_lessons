package ru.kalantyr.orm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Repository<T> implements IRepository<T> {
    private final EntityManager entityManager;
    private final Class<T> dataClass;
    private ArrayList<String> stringFields; // кэш

    public Repository(EntityManagerFactory factory, Class<T> dataClass) {
        if (factory == null)
            throw new IllegalArgumentException();
        entityManager = factory.createEntityManager();
        this.dataClass = dataClass;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(long id) {
        // вроде можно использовать getReference(), если объект уже висит в памяти
        return entityManager.find(dataClass, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<T> fullTextSearch(String text, SearchMode mode) {
        if (text == null || text.isEmpty() || text.isBlank())
            throw new IllegalArgumentException();

        var sql = createSearchQuery(mode);
        TypedQuery<T> query = entityManager.createQuery(sql, dataClass);
        query.setParameter("paramName", "%" + text + "%");
        return query.getResultList();
    }

    // понятное дело, что получившиеся запросы надо кэшировать
    private String createSearchQuery(SearchMode mode) {
        String operator;
        switch (mode)
        {
            case Exact:
                operator = "=";
                break;
            case Like:
                operator = "LIKE";
                break;
            default:
                throw new RuntimeException("Not implemented");
        }

        var whereClause = StreamSupport.stream(getStringFields().spliterator(), false) // почему нельзя применить map() сразу к iterable? :(
                .map(fn -> fn + " " + operator + " :paramName")
                .collect(Collectors.joining(" OR "));
        return "from " + getTableName() + " where " + whereClause;
    }

    private String getTableName() {
        // возвожно, имеет смысл закэшировать
        return dataClass.getSimpleName();
    }

    private Iterable<String> getStringFields() {
        if (stringFields != null)
            return stringFields;

        stringFields = new ArrayList<>();
        for (var field : dataClass.getDeclaredFields()) {
            var a = field.getAnnotation(Column.class);
            if (a == null)
                continue;
            if (field.getType() == String.class)
                stringFields.add(field.getName());
        }
        return stringFields;
    }
}
