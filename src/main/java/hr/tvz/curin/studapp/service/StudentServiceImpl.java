package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.commands.StudentCommand;
import hr.tvz.curin.studapp.domain.Student;
import hr.tvz.curin.studapp.dto.StudentCourseDTO;
import hr.tvz.curin.studapp.dto.StudentDTO;
import hr.tvz.curin.studapp.dto.StudentSecondDTO;
import hr.tvz.curin.studapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public List<StudentDTO> findStudentForLab(String jmbagContains, int ects, boolean isPaying, int age) {
        List<Student> students;
        students = studentRepository.findStudentsForLab(jmbagContains, ects, isPaying, age);
        return students.stream().map(this::mapStudentToStudentDto).collect(Collectors.toList());
    }

    @Override
    public StudentDTO save(StudentCommand command) {
        Student req = mapStudentCommandToStudent(command);
        Optional<Student> studentByJMBAG = studentRepository.findStudentByJMBAG(command.jmbag);
        if(studentByJMBAG.isPresent()){
            return null;
        }else{
            return mapStudentToStudentDto(studentRepository.save(req));
        }
    }

    @Override
    public boolean deleteStudent(String jmbag) {
        Optional<Student> student = studentRepository.findStudentByJMBAG(jmbag);
        if(student.isPresent()){
            return studentRepository.deleteStudent(student.get());
        }else{
            return false;
        }
    }

    @Override
    public StudentDTO updateStudent(StudentCommand student) {
        Student req = mapStudentCommandToStudent(student);
        try{
            Student res = studentRepository.updateStudent(req);
            return mapStudentToStudentDto(res);
        }
        catch (SQLException ex){
            return null;
        }

    }

    private StudentDTO mapStudentToStudentDto(Student student){
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        StudentDTO studentDto = new StudentDTO(
                student.JMBAG,
                student.ECTS,
                student.dateOfBirth,
                list,
                student.gender,
                student.address,
                student.city
        );

        return studentDto;
    }

    public Optional<List<StudentSecondDTO>> getStudentsByGender(String request){
        Optional<List<Student>> response = studentRepository.getStudentsByGender(request);
        if (response.isPresent()){
            return Optional.of(response.get().stream().map(this::mapStudentToStudentSecondDTO).collect(Collectors.toList()));
        }
        return Optional.empty();
    }

    public Optional<List<StudentSecondDTO>> getStudentsByCity(String request){
        Optional<List<Student>> response = studentRepository.getStudentsByCity(request);
        if (response.isPresent()){
            return Optional.of(response.get().stream().map(this::mapStudentToStudentSecondDTO).collect(Collectors.toList()));
        }
        return Optional.empty();
    }

    private Student mapStudentCommandToStudent(StudentCommand command){
        Student student = new Student(
                command.firstName,
                command.lastName,
                command.dateOfBirth,
                command.jmbag,
                command.ects,
                command.gender,
                command.address,
                command.city
        );
        return student;
    }

    private StudentSecondDTO mapStudentToStudentSecondDTO(Student student){
        return  new StudentSecondDTO(student.JMBAG,student.gender, student.city);
    }
}
