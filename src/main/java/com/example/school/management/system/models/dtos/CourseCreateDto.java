package com.example.school.management.system.models.dtos;

import com.example.school.management.system.models.enums.CourseType;

public class CourseCreateDto {

    private String title;

    private CourseType type;

    private int teacherId;

    public CourseCreateDto() {
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

    public void setType(String type) {
        this.type = CourseType.fromString(type);
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
