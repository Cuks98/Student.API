insert into student (first_name, last_name, jmbag, ects_points, date_of_birth, gender, address, city)
values ( 'Ivo', 'Ivić', '0246053232', 120, NOW(), 'male', 'neka addressa 1', 'Zagreb' );
insert into student (first_name, last_name, jmbag, ects_points, date_of_birth, gender, address, city)
values ( 'Marko', 'Ivić', '0246053233', 120, NOW(), 'male', 'neka addressa 2', 'Split' );
insert into student (first_name, last_name, jmbag, ects_points, date_of_birth, gender, address, city)
values ( 'Pero', 'Perić', '0246053234', 5, NOW(), 'male', 'neka addressa 3', 'Varaždin' );
insert into course (name, ects_points)
values ( 'Web aplikacije u Javi', 6 );
insert into course(name, ects_points)
values ('Programiranje u Javi', 5);
insert into student_course(student_id, course_id)
values ( 1, 1 );
insert into student_course(student_id, course_id)
values ( 1, 2 );
insert into student_course(student_id, course_id)
values ( 2, 1 );
insert into student_course(student_id, course_id)
values ( 2, 2 );