package com.example.school.management.system.repositories.contracts;

import com.example.school.management.system.models.filters.FilterOptions;

import java.util.List;

public interface FilteringRepository<T> {
    List<T> getAll(FilterOptions filterOptions);
    public Long getCount(FilterOptions filterOptions, T entity);
}
