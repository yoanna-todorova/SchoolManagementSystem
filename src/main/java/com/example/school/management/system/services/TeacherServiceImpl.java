package com.example.school.management.system.services;

import com.example.school.management.system.exceptions.EntityNotFoundException;
import com.example.school.management.system.exceptions.IllegalOperationException;
import com.example.school.management.system.models.Teacher;
import com.example.school.management.system.models.filters.FilterOptions;
import com.example.school.management.system.repositories.contracts.FilteringRepository;
import com.example.school.management.system.repositories.contracts.TeacherRepository;
import com.example.school.management.system.services.contracts.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final FilteringRepository<Teacher> filteringRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository,
                              FilteringRepository<Teacher> filteringRepository) {
        this.teacherRepository = teacherRepository;
        this.filteringRepository = filteringRepository;
    }

    @Override
    public void add(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void remove(int id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        Teacher teacherToCheck = optionalTeacher.orElse(null);
        if (teacherToCheck == null)
            throw new EntityNotFoundException("Teacher with such id does not exist!");
        if (!teacherToCheck.getCourses().isEmpty())
            throw new IllegalOperationException("This teacher has courses assigned to.");

        teacherRepository.delete(teacherToCheck);
    }

    @Override
    public void modify(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Optional<Teacher> getById(int id) {
        return teacherRepository.findById(id);
    }

    @Override
    public List<Teacher> getAll(FilterOptions filterOptions) {
        return filteringRepository.getAll(filterOptions);
    }

   /* public List<Teacher> getAllSorted(){
        return teacherRepository.findAll(Sort.by(Sort.Direction.ASC, Teacher.class.getName()));
    }*/

    @Override
    public Long getCount(FilterOptions filterOptions, Teacher teacher) {
        return filteringRepository.getCount(filterOptions, teacher);
    }
}
