package com.example.school.management.system.controllers;

import com.example.school.management.system.exceptions.EntityDuplicateException;
import com.example.school.management.system.exceptions.EntityNotFoundException;
import com.example.school.management.system.helpers.CourseMapper;
import com.example.school.management.system.models.Course;
import com.example.school.management.system.models.Student;
import com.example.school.management.system.models.Teacher;
import com.example.school.management.system.models.dtos.CourseCreateDto;
import com.example.school.management.system.models.dtos.CourseDisplayDto;
import com.example.school.management.system.models.filters.FilterOptions;
import com.example.school.management.system.services.contracts.CourseService;
import com.example.school.management.system.services.contracts.StudentService;
import com.example.school.management.system.services.contracts.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sms/courses")
public class CourseRestController {

    private final CourseService courseService;

    private final TeacherService teacherService;

    private final StudentService studentService;

    @Autowired
    public CourseRestController(CourseService courseService, TeacherService teacherService, StudentService studentService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @GetMapping
    public List<CourseDisplayDto> getAll(FilterOptions filterOptions) {
        List<CourseDisplayDto> result = new ArrayList<>();
        try {
            courseService.getAll(filterOptions).forEach(course -> result.add(CourseMapper.fromCourse(course)));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return result;
    }

    @GetMapping("/course/{id}")
    public CourseDisplayDto get(@PathVariable int id) {
        try {
            return CourseMapper.fromCourse(courseService.getById(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/count")
    public Long getCount(FilterOptions filterOptions) {
        return courseService.getCount(filterOptions, new Course());
    }

    @PostMapping("/course")
    public CourseDisplayDto create(@RequestBody CourseCreateDto courseCreateDto) {
        Course course = CourseMapper.fromCourseDto(courseCreateDto);
        try {
            Teacher teacher = teacherService.getById(courseCreateDto.getTeacherId());
            courseService.add(course, teacher);
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return CourseMapper.fromCourse(course);
    }

    @PutMapping("/course/{id}")
    public void update(@RequestBody CourseCreateDto courseDto,
                       @PathVariable int id) {
        try {
            Course course = courseService.getById(id);
            Teacher teacher = course.getTeacher();
            if (courseDto.getTeacherId() != 0) {
                teacher = teacherService.getById(courseDto.getTeacherId());
            }
            course = CourseMapper.fromCourseCreateDto(course, courseDto);
            courseService.modify(course, teacher);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/course/{id}/addStudent/{idStudent}")
    public void addStudent(@PathVariable int id, @PathVariable int idStudent) {
        try {
            Course course = courseService.getById(id);
            Student student = studentService.getById(idStudent);
            courseService.addStudent(student, course);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/course/{id}/removeStudent/{idStudent}")
    public void removeStudent(@PathVariable int id, @PathVariable int idStudent) {
        try {
            Course course = courseService.getById(id);
            Student student = studentService.getById(idStudent);
            courseService.removeStudent(student, course);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/course/{id}")
    public void delete(@PathVariable int id) {
        try {
            courseService.remove(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


}
