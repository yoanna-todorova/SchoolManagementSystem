package com.example.school.management.system.repositories;

import com.example.school.management.system.models.filters.FilterOptions;
import com.example.school.management.system.repositories.contracts.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCrudRepository<T> extends AbstractIdentifiableRepository<T> implements CrudRepository<T> {

    protected AbstractCrudRepository(SessionFactory sessionFactory, Class<T> clazz) {
        super(sessionFactory, clazz);
    }

    @Override
    public void add(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void remove(int id) {
        T entityToDelete = getById(id);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(entityToDelete);
            session.getTransaction().commit();
        }
    }

    @Override
    public void modify(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public Long getCount(FilterOptions filterOptions, T entity) {
        try (Session session = sessionFactory.openSession()) {
            List<String> filters = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();

            Query<Long> query = session.createQuery(String.format("SELECT COUNT(e.id) FROM %s e ",
                    entity.getClass().getSimpleName()), Long.class);
            query.setProperties(params);
            return query.uniqueResult() != null ? query.uniqueResult() : 0;
        }
    }
}
