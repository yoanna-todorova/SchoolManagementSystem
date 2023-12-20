package com.example.school.management.system.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

public class StudentDisplayDto {
    private String name;

    private int age;

    private int group;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<CourseDisplayDto> courses;

    public StudentDisplayDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public Set<CourseDisplayDto> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseDisplayDto> courses) {
        this.courses = courses;
    }
}
