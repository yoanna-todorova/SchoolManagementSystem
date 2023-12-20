package com.example.school.management.system.services.contracts;

import com.example.school.management.system.models.Course;
import com.example.school.management.system.models.Student;
import com.example.school.management.system.models.Teacher;
import com.example.school.management.system.models.filters.FilterOptions;

import java.util.List;

public interface CourseService {

    void add(Course course, Teacher teacher);

    void remove(int id);

    void modify(Course course, Teacher teacher);

    Course getById(int id);

    List<Course> getAll(FilterOptions filterOptions);

    void addStudent(Student student, Course course);

    void removeStudent(Student student, Course course);

    Long getCount(FilterOptions filterOptions, Course course);
}
