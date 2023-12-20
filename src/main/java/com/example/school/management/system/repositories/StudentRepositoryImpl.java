package com.example.school.management.system.repositories;

import com.example.school.management.system.models.Student;
import com.example.school.management.system.models.filters.FilterOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepositoryImpl extends AbstractCrudRepository<Student> {

    @Autowired
    protected StudentRepositoryImpl(SessionFactory sessionFactory, Class<Student> clazz) {
        super(sessionFactory, clazz);
    }

    @Override
    public List<Student> getAll(FilterOptions filterOptions) {
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

            filterOptions.getAge().ifPresent(value -> {
                filters.add("s.birthdate < :date");
                params.put("date", LocalDate.now().minusYears(value));
            });


            StringBuilder queryString = new StringBuilder("SELECT s FROM Student s " +
                    "LEFT JOIN s.group g " +
                    "LEFT JOIN s.courses c");

            if (!filters.isEmpty()) {
                queryString
                        .append(" where ")
                        .append(String.join(" and ", filters));
            }
            Query<Student> query = session.createQuery(queryString.toString(), Student.class);
            query.setProperties(params);
            return query.list();
        }
    }
}
