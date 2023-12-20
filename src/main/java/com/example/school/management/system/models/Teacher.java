package com.example.school.management.system.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "birthdate")
    private LocalDate birthdate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "teachers_groups",
            joinColumns = {@JoinColumn(name = "teacher_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private Set<Group> groups;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    private Set<Course> courses;

    public Teacher() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
