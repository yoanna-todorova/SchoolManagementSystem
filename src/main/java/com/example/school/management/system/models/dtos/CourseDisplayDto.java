package com.example.school.management.system.models.dtos;

import com.example.school.management.system.models.enums.CourseType;

public class CourseDisplayDto {

    private String title;
    private CourseType type;

    public CourseDisplayDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

}
