package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll();
    Optional<Student> findStudentByJMBAG(String JMBAG);

    List<Student> findStudentsForLab(String jmbag, int ects, boolean isPaying, int age);
}
