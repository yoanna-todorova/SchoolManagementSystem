package com.example.school.management.system.controllers;

import com.example.school.management.system.exceptions.EntityDuplicateException;
import com.example.school.management.system.exceptions.EntityNotFoundException;
import com.example.school.management.system.exceptions.IllegalOperationException;
import com.example.school.management.system.helpers.ParticipantsMapper;
import com.example.school.management.system.helpers.TeacherMapper;
import com.example.school.management.system.models.dtos.ParticipantsDisplayDto;
import com.example.school.management.system.models.filters.FilterOptions;
import com.example.school.management.system.models.Teacher;
import com.example.school.management.system.models.dtos.TeacherCreateDto;
import com.example.school.management.system.models.dtos.TeacherDisplayDto;
import com.example.school.management.system.services.TeacherServiceImpl;
import com.example.school.management.system.services.contracts.StudentService;
import com.example.school.management.system.services.contracts.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sms/teachers")
public class TeacherRestController {
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final TeacherMapper teacherMapper;

    @Autowired
    public TeacherRestController(TeacherService teacherService, StudentService studentService, TeacherMapper teacherMapper) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.teacherMapper = teacherMapper;
    }

    @GetMapping
    public List<TeacherDisplayDto> getAll(FilterOptions filterOptions) {

        List<TeacherDisplayDto> result = new ArrayList<>();
        teacherService.getAll(filterOptions).forEach(teacher -> result.add(teacherMapper.fromTeacher(teacher)));
        return result;
    }

    @GetMapping("teacher/{id}")
    public TeacherDisplayDto get(@PathVariable int id) {
        try {
            return teacherMapper.fromTeacher(teacherService.getById(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/count")
    public Long getCount(FilterOptions filterOptions) {
        return teacherService.getCount(filterOptions, new Teacher());
    }

    @GetMapping("/students")
    public List<ParticipantsDisplayDto> getAllStudentsTeachers(FilterOptions filterOptions) {
        List<ParticipantsDisplayDto> result = new ArrayList<>();
        teacherService.getAll(filterOptions).forEach(teacher -> result.add(ParticipantsMapper.fromTeacher(teacher)));
        studentService.getAll(filterOptions).forEach(student -> result.add(ParticipantsMapper.fromStudent(student)));

        return result;
    }

    @PostMapping("/teacher")
    public Teacher create(@RequestBody TeacherCreateDto teacherCreateDto) {
        Teacher teacher = teacherMapper.fromTeacherDto(teacherCreateDto);
        try {
            teacherService.add(teacher);
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return teacher;
    }

    @PutMapping("/teacher/{id}")
    public void update(@RequestBody TeacherCreateDto teacherCreateDto,
                       @PathVariable int id) {
        try {
            Optional<Teacher> optionalTeacher = teacherService.getById(id);
            Teacher teacher = optionalTeacher.orElse(null);
            if (teacher == null) return;
            teacher = teacherMapper.fromTeacherDto(teacher, teacherCreateDto);
            teacherService.modify(teacher);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/teacher/{id}")
    public void delete(@PathVariable int id) {
        try {
            teacherService.remove(id);
        } catch (IllegalOperationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
