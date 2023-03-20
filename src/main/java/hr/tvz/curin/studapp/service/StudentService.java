package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDTO> findAll();

    Optional<StudentDTO> findStudentByJMBAG(String JMBAG);

    List<StudentDTO> findStudentForLab(String jmbagContains, int ects, boolean isPaying, int age);
}
