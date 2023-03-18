package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.domain.Student;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentMockRepository implements StudentRepository{

    private List<Student> studentList;
    public StudentMockRepository(){
        studentList = new ArrayList<Student>();
        studentList.add(new Student(
                "Pero",
                "Perić",
                LocalDate.of(1990, 1, 8),
                0000000000L,
                123
                )
        );
        studentList.add(new Student(
                        "Ivo",
                        "Ivić",
                        LocalDate.of(2020, 1, 8),
                        0000000001L,
                        123
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
                .filter(x -> x.JMBAG == Long.parseLong(JMBAG))
                .findFirst();
    }
}
