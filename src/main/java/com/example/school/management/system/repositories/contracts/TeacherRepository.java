package com.example.school.management.system.repositories.contracts;

import com.example.school.management.system.models.Course;
import com.example.school.management.system.models.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
}
