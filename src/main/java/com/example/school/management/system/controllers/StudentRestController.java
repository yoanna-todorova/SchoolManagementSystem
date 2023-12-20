package com.example.school.management.system.controllers;

import com.example.school.management.system.exceptions.EntityDuplicateException;
import com.example.school.management.system.exceptions.EntityNotFoundException;
import com.example.school.management.system.helpers.StudentMapper;
import com.example.school.management.system.models.Student;
import com.example.school.management.system.models.dtos.StudentCreateDto;
import com.example.school.management.system.models.dtos.StudentDisplayDto;
import com.example.school.management.system.models.filters.FilterOptions;
import com.example.school.management.system.services.contracts.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sms/students")
public class StudentRestController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentRestController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @GetMapping
    public List<StudentDisplayDto> getAll(FilterOptions filterOptions) {

        List<StudentDisplayDto> result = new ArrayList<>();
        studentService.getAll(filterOptions).forEach(student -> result.add(studentMapper.fromStudent(student)));
        return result;
    }

    @GetMapping("/count")
    public Long getCount(FilterOptions filterOptions) {
        return studentService.getCount(filterOptions, new Student());
    }


    @GetMapping("student/{id}")
    public StudentDisplayDto get(@PathVariable int id) {
        try {
            return studentMapper.fromStudent(studentService.getById(id));

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/student")
    public Student create(@RequestBody StudentCreateDto studentCreateDto) {
        Student student = studentMapper.fromStudentDto(studentCreateDto);
        try {
            studentService.add(student, studentCreateDto.getGroupId());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return student;
    }

    @PutMapping("/student/{id}")
    public void update(@RequestBody StudentCreateDto studentCreateDto,
                       @PathVariable int id) {
        try {
            Student student = studentService.getById(id);
            student = studentMapper.fromStudentDto(student, studentCreateDto);
            studentService.modify(student);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/student/{id}")
    public void delete(@PathVariable int id) {
        try {
            studentService.remove(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
