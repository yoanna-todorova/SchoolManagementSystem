package com.example.school.management.system.repositories.contracts;

public interface IdentifiableRepository<T> {
    T getById(int id);
}
