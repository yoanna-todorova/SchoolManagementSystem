package com.example.school.management.system.helpers;

import com.example.school.management.system.models.Student;
import com.example.school.management.system.models.Teacher;
import com.example.school.management.system.models.dtos.ParticipantsDisplayDto;

public class ParticipantsMapper {

    private static final String STUDENT_TYPE = "STUDENT";
    private static final String TEACHER_TYPE = "TEACHER";

    public static ParticipantsDisplayDto fromStudent(Student student) {
        ParticipantsDisplayDto participantsDisplayDto = new ParticipantsDisplayDto();
        participantsDisplayDto.setName(student.getName());
        participantsDisplayDto.setType(STUDENT_TYPE);
        return participantsDisplayDto;
    }

    public static ParticipantsDisplayDto fromTeacher(Teacher teacher) {
        ParticipantsDisplayDto participantsDisplayDto = new ParticipantsDisplayDto();
        participantsDisplayDto.setName(teacher.getName());
        participantsDisplayDto.setType(TEACHER_TYPE);
        return participantsDisplayDto;
    }
}
