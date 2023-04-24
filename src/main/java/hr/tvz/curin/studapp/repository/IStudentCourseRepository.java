package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.domain.StudentCourse;

import java.util.List;
import java.util.Optional;

public interface IStudentCourseRepository {
    Optional<List<StudentCourse>> all();
    Optional<StudentCourse> getById(long id);
    Optional<StudentCourse> add(StudentCourse request);
    Optional<StudentCourse> update(StudentCourse request);
    boolean delete(long id);
    Optional<List<StudentCourse>> getByCourseId(long id);
    Optional<List<StudentCourse>> getByStudentId(long id);
}
