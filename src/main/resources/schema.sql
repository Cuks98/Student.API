create table if not exists student (
    id identity,
    jmbag varchar(10) not null unique,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    ects_points number not null,
    date_of_birth date not null,
    aboutMeHr text not null,
    aboutMeEn text not null
);
create table if not exists course (
    id identity,
    name varchar(100) not null,
    ects_points number not null
);
create table if not exists student_course (
    id identity,
    student_id bigint,
    course_id bigint,
    constraint fk_student foreign key (student_id) references student(id),
    constraint fk_course foreign key (course_id) references course(id)
);

create table if not exists _user (
    id identity,
    username varchar(50),
    password varchar(100),
    first_name varchar(100),
    last_name varchar(100)
);

create table if not exists authority (
    id identity,
    name varchar(50)
);

create table if not exists user_authority (
    id identity,
    user_id bigint,
    authority_id bigint,
    constraint fk_user foreign key (user_id) references _user(id),
    constraint fk_authority foreign key (authority_id) references authority(id)
);

create table if not exists _login
(
    id             identity,
    username       nvarchar(100),
    user_role      nvarchar(50),
    date_of_login  DATETIME not null,
    date_of_logout DATETIME null
);

alter table student add gender varchar(100) null;
alter table student add address varchar(100) null;
alter table student add city varchar(100) null;

