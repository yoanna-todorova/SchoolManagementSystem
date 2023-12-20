package com.example.school.management.system.repositories;

import com.example.school.management.system.exceptions.EntityNotFoundException;
import com.example.school.management.system.repositories.contracts.IdentifiableRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractIdentifiableRepository<T> implements IdentifiableRepository<T> {
    protected final SessionFactory sessionFactory;
    protected final Class<T> clazz;

    protected AbstractIdentifiableRepository(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    @Override
    public T getById(int id) {
        String query = String.format("from %s where id = :id", clazz.getSimpleName());
        String notFoundError = String.format("%s with id %s not found", clazz.getSimpleName(), id);
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery(query, clazz)
                    .setParameter("id", id)
                    .uniqueResultOptional()
                    .orElseThrow(() -> new EntityNotFoundException(notFoundError));
        }
    }
}

