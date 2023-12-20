package com.example.school.management.system.models.dtos;

import java.time.LocalDate;

public class TeacherCreateDto {

    private String name;

    private LocalDate birthdate;

    public TeacherCreateDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
