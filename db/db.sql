create table teachers
(
    id        int auto_increment
        primary key,
    name      varchar(255) not null,
    birthdate date         not null
);

create table courses
(
    id         int auto_increment
        primary key,
    name       varchar(255) not null,
    type       varchar(255) not null,
    teacher_id int          not null,
    constraint courses_teachers_id_fk
        foreign key (teacher_id) references teachers (id)
);

create table `groups`
(
    id         int auto_increment
        primary key,
    teacher_id int not null,
    constraint groups_teachers_id_fk
        foreign key (teacher_id) references teachers (id)
);

create table students
(
    name      varchar(255) not null,
    id        int auto_increment
        primary key,
    group_id  int          not null,
    birthdate date         not null,
    constraint students_groups_id_fk
        foreign key (group_id) references `groups` (id)
);

create table students_courses
(
    id         int auto_increment
        primary key,
    student_id int not null,
    course_id  int not null,
    constraint students_courses_courses_id_fk
        foreign key (course_id) references courses (id)
            on update cascade on delete cascade,
    constraint students_courses_students_id_fk
        foreign key (student_id) references students (id)
            on update cascade on delete cascade
);

create table teachers_groups
(
    id         int auto_increment
        primary key,
    teacher_id int not null,
    group_id   int not null,
    constraint teachers_groups_groups_id_fk
        foreign key (group_id) references `groups` (id),
    constraint teachers_groups_teachers_id_fk
        foreign key (teacher_id) references teachers (id)
);

