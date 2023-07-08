insert into student (first_name, last_name, jmbag, ects_points, date_of_birth, gender, address, city, aboutMeHr, aboutMeEn)
values ( 'Ivo', 'Ivić', '0246053232', 120, NOW(), 'male', 'neka addressa 3', 'Varaždin', 'Neka recenica 1. Neka recenica 2.','She couldnt decide of the glass was half empty or half full so she drank it. It was difficult for Mary to admit that most of her workout consisted of exercising poor judgment.');
insert into student (first_name, last_name, jmbag, ects_points, date_of_birth, gender, address, city, aboutMeHr, aboutMeEn)
values ( 'Marko', 'Ivić', '0246053233', 120, NOW(), 'male', 'neka addressa 3', 'Varaždin', 'Neka recenica 1. Neka recenica 2.', 'She couldnt decide of the glass was half empty or half full so she drank it. It was difficult for Mary to admit that most of her workout consisted of exercising poor judgment.');
insert into student (first_name, last_name, jmbag, ects_points, date_of_birth, gender, address, city, aboutMeHr, aboutMeEn)
values ( 'Pero', 'Perić', '0246053234', 5, NOW(), 'male', 'neka addressa 3', 'Varaždin', 'Neka recenica 1. Neka recenica 2.', 'She couldnt decide of the glass was half empty or half full so she drank it. It was difficult for Mary to admit that most of her workout consisted of exercising poor judgment.' );
insert into course (name, ects_points)
values ( 'Web aplikacije u Javi', 6 );
insert into course(name, ects_points)
values ('Programiranje u Javi', 5);
insert into course(name, ects_points)
values ('C++', 6);
insert into course(name, ects_points)
values ('Uredsko poslovanje', 4);
insert into student_course(student_id, course_id)
values ( 1, 1 );
insert into student_course(student_id, course_id)
values ( 1, 2 );
insert into student_course(student_id, course_id)
values ( 2, 1 );
insert into student_course(student_id, course_id)
values ( 2, 2 );
insert into student_course(student_id, course_id)
values ( 3, 1 );
insert into student_course(student_id, course_id)
values ( 3, 3 );
insert into student_course(student_id, course_id)
values ( 3, 4 );
--password = admin
insert into _user (username, password, first_name, last_name)
values ( 'Admin', '$2y$10$yOE77H03BdLzMm2BNkX4dOVxpgwWJKrHxJaa8McwtPsTyU6lE91Oe', 'Admin', 'Administrator' );
--pasword = user
insert into _user (username, password, first_name, last_name)
values ( 'User', '$2a$12$tZDzKfGBx9xEry6tF6SXMOIytYvzO.8dz5yW60vyZc0oHKlHu39aO', 'User', 'User' );
insert into authority(name) values ('ROLE_ADMIN');
insert into authority(name) values ('ROLE_USER');
insert into user_authority(user_id, authority_id) values ( 1, 1 );
insert into user_authority(user_id, authority_id) values ( 2, 2 );
insert into _login(username, user_role, date_of_login, date_of_logout) values ('Admin', 'ROLE_ADMIN', NOW(), NOW());
insert into _login(username, user_role, date_of_login, date_of_logout) values ('Admin', 'ROLE_ADMIN', NOW(), null);
insert into _login(username, user_role, date_of_login, date_of_logout) values ('User', 'ROLE_USER', '2023-05-22T17:35:17.923624', null);
insert into _login(username, user_role, date_of_login, date_of_logout) values ('User', 'ROLE_USER', NOW(), null);

--     id             identity,
--     username       nvarchar(100),
--     user_role      nvarchar(50),
--     date_of_login  date not null,
--     date_of_logout date not null
