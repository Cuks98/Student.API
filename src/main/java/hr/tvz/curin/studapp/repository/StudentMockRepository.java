package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.domain.Student;
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
public class StudentMockRepository implements StudentRepository{

    private List<Student> studentList;
    public StudentMockRepository(){
        studentList = new ArrayList<Student>();
        studentList.add(new Student(
                "Pero",
                "Perić",
                LocalDate.of(1990, 1, 8),
                "00000000000",
                123
                )
        );
        studentList.add(new Student(
                        "Ivo",
                        "Ivić",
                        LocalDate.of(2020, 1, 8),
                "00000000001",
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
                .filter(x-> x.JMBAG.startsWith(jmbag)
                        && x.ECTS > ects
                        && x.shouldStudentPayFee == isPaying
                        && Period.between(x.dateOfBirth, LocalDate.now()).getYears() > 25)
                .collect(Collectors.toList());
    }
}
