package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.domain.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> getAll();
    Optional<Course> getCourseById(long id);
    Optional<Course> add(Course request);
    Optional<Course> update(Course request);
    boolean delete(long id);
}
