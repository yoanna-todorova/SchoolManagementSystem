package com.example.school.management.system.repositories;

import com.example.school.management.system.models.Course;
import com.example.school.management.system.models.enums.CourseType;
import com.example.school.management.system.models.filters.FilterOptions;
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
public class CourseRepositoryImpl extends AbstractCrudRepository<Course> {
    @Autowired
    protected CourseRepositoryImpl(SessionFactory sessionFactory, Class<Course> clazz) {
        super(sessionFactory, clazz);
    }

    @Override
    public List<Course> getAll(FilterOptions filterOptions) {
        try (Session session = sessionFactory.openSession()) {
            List<String> filters = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();

            filterOptions.getCourseTitle().ifPresent(value -> {
                filters.add("c.name like :courseName");
                params.put("courseName", String.format("%%%s%%", value));
            });

            filterOptions.getCourseType().ifPresent(value -> {
                filters.add("c.type = :type");
                params.put("type", CourseType.fromString(value));
            });

            StringBuilder queryString = new StringBuilder("SELECT c FROM Course c ");

            if (!filters.isEmpty()) {
                queryString
                        .append(" where ")
                        .append(String.join(" and ", filters));
            }
            Query<Course> query = session.createQuery(queryString.toString(), Course.class);
            query.setProperties(params);
            return query.list();
        }
    }

    @Override
    public Long getCount(FilterOptions filterOptions, Course course) {
        try (Session session = sessionFactory.openSession()) {
            List<String> filters = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();

            filterOptions.getCourseType().ifPresent(value -> {
                filters.add("c.type = :type");
                params.put("type", CourseType.fromString(value));
            });

            StringBuilder queryString = new StringBuilder("SELECT COUNT(c.id) FROM Course c ");

            if (!filters.isEmpty()) {
                queryString
                        .append(" where ")
                        .append(String.join(" and ", filters));
            }
            Query<Long> query = session.createQuery(queryString.toString(), Long.class);
            query.setProperties(params);
            return query.uniqueResult() != null ? query.uniqueResult() : 0;
        }
    }
}
