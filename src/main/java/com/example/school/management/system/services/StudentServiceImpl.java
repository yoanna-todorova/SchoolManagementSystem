package com.example.school.management.system.services;

import com.example.school.management.system.models.Group;
import com.example.school.management.system.models.Student;
import com.example.school.management.system.models.filters.FilterOptions;
import com.example.school.management.system.repositories.contracts.CrudRepository;
import com.example.school.management.system.repositories.contracts.IdentifiableRepository;
import com.example.school.management.system.services.contracts.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final CrudRepository<Student> studentRepository;
    private final IdentifiableRepository<Group> groupRepository;

    @Autowired
    public StudentServiceImpl(CrudRepository<Student> studentRepository, IdentifiableRepository<Group> groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public void add(Student student, int groupId) {
        Group group = groupRepository.getById(groupId);
        student.setGroup(group);
        studentRepository.add(student);
    }

    @Override
    public void remove(int id) {
        studentRepository.remove(id);
    }

    @Override
    public void modify(Student student) {
        studentRepository.modify(student);
    }

    @Override
    public Student getById(int id) {
        return studentRepository.getById(id);
    }

    @Override
    public List<Student> getAll(FilterOptions filterOptions) {
        return studentRepository.getAll(filterOptions);
    }

    @Override
    public Long getCount(FilterOptions filterOptions, Student student) {
        return studentRepository.getCount(filterOptions, student);
    }
}
