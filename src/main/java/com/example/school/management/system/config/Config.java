package com.example.school.management.system.config;

import com.example.school.management.system.models.Course;
import com.example.school.management.system.models.Group;
import com.example.school.management.system.models.Student;
import com.example.school.management.system.models.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Class<Student> studentClass() {
        return Student.class;
    }

    @Bean
    public Class<Teacher> teacherClass() {
        return Teacher.class;
    }

    @Bean
    public Class<Group> groupClass() {
        return Group.class;
    }

    @Bean
    public Class<Course> courseClass() {
        return Course.class;
    }
}
