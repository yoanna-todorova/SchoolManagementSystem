package com.example.school.management.system.services;

import com.example.school.management.system.models.Course;
import com.example.school.management.system.models.Student;
import com.example.school.management.system.models.Teacher;
import com.example.school.management.system.models.filters.FilterOptions;
import com.example.school.management.system.repositories.contracts.CrudRepository;
import com.example.school.management.system.services.contracts.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CrudRepository<Course> courseRepository;

    private final CrudRepository<Student> studentRepository;

    @Autowired
    public CourseServiceImpl(CrudRepository<Course> courseRepository, CrudRepository<Student> studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void add(Course course, Teacher teacher) {
        course.setTeacher(teacher);
        courseRepository.add(course);
    }

    @Override
    public void remove(int id) {
        courseRepository.remove(id);
    }

    @Override
    public void modify(Course course, Teacher teacher) {
        course.setTeacher(teacher);
        courseRepository.modify(course);
    }

    @Override
    public Course getById(int id) {
        return courseRepository.getById(id);
    }

    @Override
    public List<Course> getAll(FilterOptions filterOptions) {
        return courseRepository.getAll(filterOptions);
    }

    @Override
    public void addStudent(Student student, Course course) {
        course.addStudent(student);
        courseRepository.modify(course);
    }

    @Override
    public void removeStudent(Student student, Course course) {
        student.getCourses().remove(course);
        course.getStudents().remove(student);
        studentRepository.modify(student);
        courseRepository.modify(course);
    }

    @Override
    public Long getCount(FilterOptions filterOptions, Course course) {
        return courseRepository.getCount(filterOptions, course);
    }
}
