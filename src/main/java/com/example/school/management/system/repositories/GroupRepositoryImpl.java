package com.example.school.management.system.repositories;

import com.example.school.management.system.models.Group;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GroupRepositoryImpl extends AbstractIdentifiableRepository<Group> {
    @Autowired
    public GroupRepositoryImpl(SessionFactory sessionFactory, Class<Group> clazz) {
        super(sessionFactory, clazz);
    }
}
