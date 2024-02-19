package com.example.school.management.system;

import com.example.school.management.system.models.Course;
import com.example.school.management.system.models.Group;
import com.example.school.management.system.models.Student;
import com.example.school.management.system.models.Teacher;
import com.example.school.management.system.models.enums.CourseType;
import com.example.school.management.system.repositories.contracts.FilteringRepository;
import com.example.school.management.system.services.CourseServiceImpl;
import com.example.school.management.system.services.contracts.CourseService;
import com.example.school.management.system.services.contracts.TeacherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class SchoolManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolManagementSystemApplication.class, args);
    }
        @Bean
        CommandLineRunner commandLineRunner(TeacherService teacherService){

            return args -> {
                Course course = new Course();
                course.setName("Information Technology");
                course.setType(CourseType.MAIN);

                Group group = new Group();

                Teacher teacher = new Teacher();
                teacher.setBirthdate(LocalDate.of(1999, 1, 18));
                teacher.setName("Yoanna Todorova");
               // teacher.setCourses(Set.of(course));
              //  teacher.setGroups(Set.of(group));
                teacherService.add(teacher);
            };
        }
    }
