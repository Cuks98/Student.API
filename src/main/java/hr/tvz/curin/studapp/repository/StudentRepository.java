package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.commands.StudentCommand;
import hr.tvz.curin.studapp.domain.Student;
import hr.tvz.curin.studapp.dto.StudentDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll();
    Optional<Student> findStudentByJMBAG(String JMBAG);

    List<Student> findStudentsForLab(String jmbag, int ects, boolean isPaying, int age);
    Student save(Student student);
    boolean deleteStudent(Student student);
    Student updateStudent(Student student) throws SQLException;
    Optional<List<Student>> getStudentsByGender(String request);
    Optional<List<Student>> getStudentsByCity(String request);
}
