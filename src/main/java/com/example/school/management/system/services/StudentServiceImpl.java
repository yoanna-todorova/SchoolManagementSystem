package com.example.school.management.system.services;

import com.example.school.management.system.exceptions.EntityNotFoundException;
import com.example.school.management.system.exceptions.IllegalOperationException;
import com.example.school.management.system.models.Group;
import com.example.school.management.system.models.Student;
import com.example.school.management.system.models.Teacher;
import com.example.school.management.system.models.filters.FilterOptions;
import com.example.school.management.system.repositories.contracts.FilteringRepository;
import com.example.school.management.system.repositories.contracts.GroupRepository;
import com.example.school.management.system.repositories.contracts.StudentRepository;
import com.example.school.management.system.services.contracts.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final FilteringRepository<Student> filteringRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, GroupRepository groupRepository, FilteringRepository<Student> filteringRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.filteringRepository = filteringRepository;
    }

    @Override
    public void add(Student student, int groupId) {
        Group group = groupRepository.findById(groupId).orElse(null);
        if (group == null)
            throw new IllegalOperationException("No student could be added because " +
                    "group with such number does not exist!");
        student.setGroup(group);
        studentRepository.save(student);
    }

    @Override
    public void remove(int id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student student = optionalStudent.orElse(null);
        if (student == null)
            throw new EntityNotFoundException("Teacher with such id does not exist!");
        studentRepository.delete(student);
    }

    @Override
    public void modify(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student getById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> getAll(FilterOptions filterOptions) {
        return filteringRepository.getAll(filterOptions);
    }

    @Override
    public Long getCount(FilterOptions filterOptions, Student student) {
        return filteringRepository.getCount(filterOptions, student);
    }
}
