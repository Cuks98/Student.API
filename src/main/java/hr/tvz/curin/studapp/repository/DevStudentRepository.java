package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.domain.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("dev")
public class DevStudentRepository implements StudentRepository{
    @Override
    public List<Student> findAll() {
        return new ArrayList<>();
    }

    @Override
    public Optional<Student> findStudentByJMBAG(String JMBAG) {
        return Optional.empty();
    }

    @Override
    public List<Student> findStudentsForLab(String jmbag, int ects, boolean isPaying, int age) {
        return new ArrayList<>();
    }
}
