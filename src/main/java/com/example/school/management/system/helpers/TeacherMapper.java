package com.example.school.management.system.helpers;

import com.example.school.management.system.models.Teacher;
import com.example.school.management.system.models.dtos.TeacherCreateDto;
import com.example.school.management.system.models.dtos.TeacherDisplayDto;
import com.example.school.management.system.services.contracts.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class TeacherMapper {

    private final TeacherService teacherService;

    @Autowired
    public TeacherMapper(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public TeacherDisplayDto fromTeacher(Optional<Teacher> optTeacher) {
        Teacher teacher = optTeacher.orElse(null);
        if (teacher == null) return null;
        TeacherDisplayDto teacherDisplayDto = new TeacherDisplayDto();
        teacherDisplayDto.setName(teacher.getName());
        Set<String> courses = new HashSet<>();
        teacher.getCourses().forEach(course -> courses.add(course.getName()));
        teacherDisplayDto.setCourses(courses);
        Set<Integer> groups = new HashSet<>();
        teacher.getGroups().forEach(group -> groups.add(group.getId()));
        teacherDisplayDto.setGroups(groups);
        return teacherDisplayDto;
    }

    public TeacherDisplayDto fromTeacher(Teacher teacher) {
        TeacherDisplayDto teacherDisplayDto = new TeacherDisplayDto();
        teacherDisplayDto.setName(teacher.getName());
        Set<String> courses = new HashSet<>();
        teacher.getCourses().forEach(course -> courses.add(course.getName()));
        teacherDisplayDto.setCourses(courses);
        Set<Integer> groups = new HashSet<>();
        teacher.getGroups().forEach(group -> groups.add(group.getId()));
        teacherDisplayDto.setGroups(groups);
        return teacherDisplayDto;
    }

    public Teacher fromTeacherDto(TeacherCreateDto teacherCreateDto) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherCreateDto.getName());
        teacher.setBirthdate(teacherCreateDto.getBirthdate());
        return teacher;
    }

    public Teacher fromTeacherDto(Teacher teacher, TeacherCreateDto teacherCreateDto) {
        teacher.setName(teacherCreateDto.getName());
        teacher.setBirthdate(teacherCreateDto.getBirthdate());
        return teacher;
    }
}
