package com.example.school.management.system.repositories;

import com.example.school.management.system.models.Teacher;
import com.example.school.management.system.models.filters.FilterOptions;
import com.example.school.management.system.repositories.contracts.FilteringRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractFilteringRepository<T> implements FilteringRepository<T> {

    protected final SessionFactory sessionFactory;
    protected final Class<T> clazz;

    protected AbstractFilteringRepository(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    @Override
    public abstract List<T> getAll(FilterOptions filterOptions);

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
