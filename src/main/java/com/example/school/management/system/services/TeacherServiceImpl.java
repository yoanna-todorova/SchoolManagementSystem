package com.example.school.management.system.services;

import com.example.school.management.system.models.Teacher;
import com.example.school.management.system.models.filters.FilterOptions;
import com.example.school.management.system.repositories.contracts.CrudRepository;
import com.example.school.management.system.services.contracts.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final CrudRepository<Teacher> teacherRepository;

    @Autowired
    public TeacherServiceImpl(CrudRepository<Teacher> teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void add(Teacher teacher) {
        teacherRepository.add(teacher);
    }

    @Override
    public void remove(int id) {
        teacherRepository.remove(id);
    }

    @Override
    public void modify(Teacher teacher) {
        teacherRepository.modify(teacher);
    }

    @Override
    public Teacher getById(int id) {
        return teacherRepository.getById(id);
    }

    @Override
    public List<Teacher> getAll(FilterOptions filterOptions) {
        return teacherRepository.getAll(filterOptions);
    }

    @Override
    public Long getCount(FilterOptions filterOptions, Teacher teacher) {
        return teacherRepository.getCount(filterOptions, teacher);
    }
}
