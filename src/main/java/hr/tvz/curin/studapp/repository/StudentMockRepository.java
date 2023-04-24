package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.commands.StudentCommand;
import hr.tvz.curin.studapp.domain.Student;
import hr.tvz.curin.studapp.dto.StudentDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("!dev")
public class StudentMockRepository implements StudentRepository {

    private List<Student> studentList;

    public StudentMockRepository() {
        studentList = new ArrayList<Student>();
        studentList.add(new Student(
                        "Pero",
                        "Perić",
                        LocalDate.of(1990, 1, 8),
                        "0000000000",
                        123
                )
        );
        studentList.add(new Student(
                        "Ivo",
                        "Ivić",
                        LocalDate.of(2020, 1, 8),
                        "0000000001",
                        123
                )
        );
        studentList.add(new Student(
                        "Marko",
                        "Markić",
                        LocalDate.of(1992, 1, 8),
                        "00320100000",
                        5
                )
        );
    }

    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public Optional<Student> findStudentByJMBAG(String JMBAG) {
        return studentList.stream()
                .filter(x -> x.JMBAG.equals(JMBAG))
                .findFirst();
    }


    @Override
    public List<Student> findStudentsForLab(String jmbag, int ects, boolean isPaying, int age) {
        return studentList.stream()
                .filter(x -> x.JMBAG.startsWith(jmbag)
                        && x.ECTS > ects
                        && x.shouldStudentPayFee == isPaying
                        && Period.between(x.dateOfBirth, LocalDate.now()).getYears() > 25)
                .collect(Collectors.toList());
    }

    @Override
    public Student save(Student student) {
        studentList.add(student);
        return student;
    }

    @Override
    public boolean deleteStudent(Student student) {
        try {
            studentList.remove(student);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Student updateStudent(Student student){
        Optional<Student> stu = studentList.stream()
                .filter(x -> x.JMBAG.equals(student.JMBAG))
                .findFirst();
        if(stu.isPresent()){
            stu.get().firstName = student.firstName;
            stu.get().lastName = student.lastName;
            stu.get().ECTS = student.ECTS;
        }
        return stu.get();
    }

    @Override
    public Optional<List<Student>> getStudentsByGender(String request) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Student>> getStudentsByCity(String request) {
        return Optional.empty();
    }
}
