package com.example.school.management.system.repositories.contracts;

import com.example.school.management.system.models.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> {
}
