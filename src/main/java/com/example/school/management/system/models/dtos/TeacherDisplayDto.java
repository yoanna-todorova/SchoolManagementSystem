package com.example.school.management.system.models.dtos;

import java.util.Set;

public class TeacherDisplayDto {
    private String name;
    private Set<Integer> groups;
    private Set<String> courses;

    public TeacherDisplayDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Integer> getGroups() {
        return groups;
    }

    public void setGroups(Set<Integer> groups) {
        this.groups = groups;
    }

    public Set<String> getCourses() {
        return courses;
    }

    public void setCourses(Set<String> courses) {
        this.courses = courses;
    }
}
