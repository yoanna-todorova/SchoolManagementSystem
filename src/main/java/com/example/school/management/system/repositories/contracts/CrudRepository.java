package com.example.school.management.system.repositories.contracts;

import com.example.school.management.system.models.filters.FilterOptions;

import java.util.List;

public interface CrudRepository<T> extends IdentifiableRepository<T> {
    void add(T entity);

    void remove(int id);

    void modify(T entity);

    List<T> getAll(FilterOptions filterOptions);

    Long getCount(FilterOptions filterOptions, T entity);
}
