package com.example.school.management.system.repositories.contracts;

import com.example.school.management.system.models.Course;
import com.example.school.management.system.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
}
