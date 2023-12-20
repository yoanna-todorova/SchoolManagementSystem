package com.example.school.management.system.models.filters;

import java.util.Optional;

public class FilterOptions {
    private Optional<String> courseTitle;
    private Optional<Integer> groupId;
    private Optional<Integer> age;
    private Optional<String> courseType;

    public FilterOptions(String courseTitle, Integer groupId, Integer age, String courseType) {
        this.courseTitle = Optional.ofNullable(courseTitle);
        this.groupId = Optional.ofNullable(groupId);
        this.age = Optional.ofNullable(age);
        this.courseType = Optional.ofNullable(courseType);
    }

    public Optional<String> getCourseTitle() {
        return courseTitle;
    }

    public Optional<Integer> getGroupId() {
        return groupId;
    }

    public Optional<Integer> getAge() {
        return age;
    }

    public Optional<String> getCourseType() {
        return courseType;
    }
}
