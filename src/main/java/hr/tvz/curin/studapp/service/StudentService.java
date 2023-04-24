package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.commands.StudentCommand;
import hr.tvz.curin.studapp.domain.Student;
import hr.tvz.curin.studapp.dto.StudentDTO;
import hr.tvz.curin.studapp.dto.StudentSecondDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDTO> findAll();

    Optional<StudentDTO> findStudentByJMBAG(String JMBAG);

    List<StudentDTO> findStudentForLab(String jmbagContains, int ects, boolean isPaying, int age);
    StudentDTO save(StudentCommand command);
    boolean deleteStudent(String jmbag);
    StudentDTO updateStudent(StudentCommand student);
    Optional<List<StudentSecondDTO>> getStudentsByGender(String request);
    Optional<List<StudentSecondDTO>> getStudentsByCity(String request);
}
