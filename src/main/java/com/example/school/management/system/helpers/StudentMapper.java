package com.example.school.management.system.helpers;

import com.example.school.management.system.models.Student;
import com.example.school.management.system.models.dtos.CourseDisplayDto;
import com.example.school.management.system.models.dtos.StudentCreateDto;
import com.example.school.management.system.models.dtos.StudentDisplayDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class StudentMapper {

    public StudentMapper() {
    }

    public StudentDisplayDto fromStudent(Student student) {
        StudentDisplayDto studentDisplayDto = new StudentDisplayDto();
        studentDisplayDto.setName(student.getName());
        int age = LocalDate.now().getYear() - student.getBirthdate().getYear();
        if ((student.getBirthdate().getMonth().compareTo(LocalDate.now().getMonth()) > 0)
                || (student.getBirthdate().getMonth().compareTo(LocalDate.now().getMonth()) == 0
                && student.getBirthdate().getDayOfMonth() > LocalDate.now().getDayOfMonth()))
            age-=1;

        studentDisplayDto.setAge(age);
        studentDisplayDto.setGroup(student.getGroup().getId());
        if (!student.getCourses().isEmpty()) {
            Set<CourseDisplayDto> courseDisplayDtoSet = new HashSet<>();
            student.getCourses().forEach(course -> courseDisplayDtoSet.add(CourseMapper.fromCourse(course)));
            studentDisplayDto.setCourses(courseDisplayDtoSet);
        }
        return studentDisplayDto;
    }

    public Student fromStudentDto(StudentCreateDto studentCreateDto) {
        Student student = new Student();
        student.setName(studentCreateDto.getName());
        student.setBirthdate(studentCreateDto.getBirthdate());
        return student;
    }

    public Student fromStudentDto(Student student, StudentCreateDto studentCreateDto) {
        student.setName(studentCreateDto.getName());
        student.setBirthdate(studentCreateDto.getBirthdate());
        return student;
    }
}
