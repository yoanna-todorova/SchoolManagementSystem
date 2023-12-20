package com.example.school.management.system.models.dtos;

import java.time.LocalDate;

public class StudentCreateDto {

    private String name;

    private LocalDate birthdate;

    private int groupId;

    public StudentCreateDto() {
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
