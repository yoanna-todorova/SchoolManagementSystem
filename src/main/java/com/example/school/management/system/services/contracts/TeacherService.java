package com.example.school.management.system.services.contracts;

import com.example.school.management.system.models.Teacher;
import com.example.school.management.system.models.filters.FilterOptions;

import java.util.List;

public interface TeacherService {

    void add(Teacher teacher);

    void remove(int id);

    void modify(Teacher teacher);

    Teacher getById(int id);

    List<Teacher> getAll(FilterOptions filterOptions);

    Long getCount(FilterOptions filterOptions, Teacher teacher);
}
