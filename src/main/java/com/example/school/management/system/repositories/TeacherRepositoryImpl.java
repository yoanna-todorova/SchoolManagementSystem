package com.example.school.management.system.repositories;

import com.example.school.management.system.models.Teacher;
import com.example.school.management.system.models.filters.FilterOptions;
import com.example.school.management.system.repositories.contracts.FilteringRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TeacherRepositoryImpl extends AbstractFilteringRepository<Teacher>  {

    @Autowired
    protected TeacherRepositoryImpl(SessionFactory sessionFactory, Class<Teacher> clazz) {
       super(sessionFactory, clazz);
    }

    @Override
    public List<Teacher> getAll(FilterOptions filterOptions) {
        try (Session session = sessionFactory.openSession()) {
            List<String> filters = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();

            filterOptions.getCourseTitle().ifPresent(value -> {
                filters.add("c.name like :courseName");
                params.put("courseName", String.format("%%%s%%", value));
            });

            filterOptions.getGroupId().ifPresent(value -> {
                filters.add("g.id = :groupId");
                params.put("groupId", value);
            });

            StringBuilder queryString = new StringBuilder("SELECT DISTINCT t FROM Teacher t " +
                    "LEFT JOIN t.groups g " +
                    "LEFT JOIN t.courses c ");

            if (!filters.isEmpty()) {
                queryString
                        .append(" where ")
                        .append(String.join(" and ", filters));
            }
            Query<Teacher> query = session.createQuery(queryString.toString(), Teacher.class);
            query.setProperties(params);
            return query.list();
        }
    }
}
