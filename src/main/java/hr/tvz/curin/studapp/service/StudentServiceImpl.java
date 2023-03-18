package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.domain.Student;
import hr.tvz.curin.studapp.dto.StudentDTO;
import hr.tvz.curin.studapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(this::mapStudentToStudentDto).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDTO> findStudentByJMBAG(String JMBAG) {

        Optional<Student> student;
        student = studentRepository.findStudentByJMBAG(JMBAG);
        if(student.isPresent()){
            return student.stream().map(this::mapStudentToStudentDto).findFirst();
        }else{
            return Optional.empty();
        }
    }

    private StudentDTO mapStudentToStudentDto(Student student){
        StudentDTO studentDto = new StudentDTO(student.JMBAG, student.ECTS, student.dateOfBirth);

        return studentDto;
    }
}
