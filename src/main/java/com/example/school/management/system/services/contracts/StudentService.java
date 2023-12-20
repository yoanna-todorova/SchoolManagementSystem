package com.example.school.management.system.services.contracts;

import com.example.school.management.system.models.Student;
import com.example.school.management.system.models.filters.FilterOptions;

import java.util.List;

public interface StudentService {

    void add(Student student, int groupId);

    void remove(int id);

    void modify(Student student);

    Student getById(int id);

    List<Student> getAll(FilterOptions filterOptions);

    Long getCount(FilterOptions filterOptions, Student student);


}
