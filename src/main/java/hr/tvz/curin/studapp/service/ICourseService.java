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
}
