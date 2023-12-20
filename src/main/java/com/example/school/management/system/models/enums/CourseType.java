package com.example.school.management.system.models.enums;

public enum CourseType {
    MAIN, SECONDARY;

    public static CourseType fromString(String value) {
        for (CourseType type : CourseType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant for " + value);
    }
}
