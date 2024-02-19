package com.example.school.management.system.services;

import com.example.school.management.system.models.Course;
import com.example.school.management.system.models.Student;
import com.example.school.management.system.models.Teacher;
import com.example.school.management.system.models.filters.FilterOptions;
import com.example.school.management.system.repositories.contracts.CourseRepository;
import com.example.school.management.system.repositories.contracts.FilteringRepository;
import com.example.school.management.system.repositories.contracts.StudentRepository;
import com.example.school.management.system.services.contracts.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final FilteringRepository<Course> filteringRepository;

    @Autowired
    public CourseServiceImpl(
            CourseRepository courseRepository, StudentRepository studentRepository, FilteringRepository<Course> filteringRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.filteringRepository = filteringRepository;
    }

    @Override
    public void add(Course course, Teacher teacher) {
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    @Override
    public void remove(int id) {
        Course course = courseRepository.findById(id).orElse(null);
        assert course != null;
        courseRepository.delete(course);
    }

    @Override
    public void modify(Course course, Teacher teacher) {
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    @Override
    public Course getById(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Course> getAll(FilterOptions filterOptions) {
        return filteringRepository.getAll(filterOptions);
    }

    @Override
    public void addStudent(Student student, Course course) {
        course.addStudent(student);
        courseRepository.save(course);
    }

    @Override
    public void removeStudent(Student student, Course course) {
        student.getCourses().remove(course);
        course.getStudents().remove(student);
        studentRepository.save(student);
        courseRepository.save(course);
    }

    @Override
    public Long getCount(FilterOptions filterOptions, Course course) {
        return filteringRepository.getCount(filterOptions, course);
    }
}
