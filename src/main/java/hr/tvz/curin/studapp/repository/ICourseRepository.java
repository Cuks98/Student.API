package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByStudents_JMBAG(String jmbag);
}
