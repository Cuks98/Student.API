package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.commands.CourseCommand;
import hr.tvz.curin.studapp.domain.Course;
import hr.tvz.curin.studapp.dto.CourseDTO;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<CourseDTO> getAll();
    Optional<CourseDTO> getCourseById(long id);
    Optional<CourseDTO> add(CourseCommand request);
    Optional<CourseDTO> update(CourseCommand request);
    boolean delete(long id);
    Optional<List<CourseDTO>> getCourseByStudentJmbag(String jmbag);
    Optional<List<CourseDTO>> getAllWithJpa();
    Optional<CourseDTO> getFirstByJmbag(String jmbag);
    Optional<List<CourseDTO>> getTop(String jmbag);
    Optional<List<CourseDTO>> getStartsWith(String jmbag);
    Optional<List<CourseDTO>> getEndsWith(String jmbag);
    Optional<List<CourseDTO>> getNotIn(List<String> jmbags);
    Optional<List<CourseDTO>> getIn(List<String> jmbags);
    Optional<List<CourseDTO>> getAllOrderdBy();
    Optional<List<CourseDTO>> getIgnoreCase(String jmbag);

}
