package com.example.school.management.system.helpers;

import com.example.school.management.system.models.Course;
import com.example.school.management.system.models.dtos.CourseCreateDto;
import com.example.school.management.system.models.dtos.CourseDisplayDto;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public static CourseDisplayDto fromCourse(Course course) {
        CourseDisplayDto courseDisplayDto = new CourseDisplayDto();
        courseDisplayDto.setTitle(course.getName());
        courseDisplayDto.setType(course.getType());
        return courseDisplayDto;
    }

    public static Course fromCourseDto(CourseCreateDto courseDto) {
        Course course = new Course();
        course.setName(courseDto.getTitle());
        course.setType(courseDto.getType());
        return course;
    }

    public static Course fromCourseCreateDto(Course course, CourseCreateDto courseDto) {
        course.setName(courseDto.getTitle());
        course.setType(courseDto.getType());
        return course;
    }
}
