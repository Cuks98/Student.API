package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByStudents_JMBAG(String jmbag);
    Course findFirstByStudents_JMBAG(String jmabg);
    //List<Course> findTop3ByStudents_JMBAGOrderByStudentsEcts(String jmbag);
    List<Course> findTop3ByStudents_JMBAGContaining(String jmbag);
    List<Course> findByStudents_JMBAGStartingWith(String jmbag);
    List<Course> findByStudents_JMBAGEndingWith(String jmbag);
    List<Course> findDistinctByStudents_JMBAGNotIn(List<String> jmbags);
    List<Course> findDistinctByStudents_JMBAGIn(List<String> jmbags);
    List<Course> findAllByOrderByStudents_JMBAG();
    List<Course> findByStudents_JMBAGIgnoreCase(String jmbag);
    List<Course> findAllByOrderByEcts();
}
