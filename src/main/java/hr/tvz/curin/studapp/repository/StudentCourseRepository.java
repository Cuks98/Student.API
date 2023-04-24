package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.domain.StudentCourse;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class StudentCourseRepository implements IStudentCourseRepository{
    @Override
    public Optional<List<StudentCourse>> all() {
        return null;
    }

    @Override
    public Optional<StudentCourse> getById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<StudentCourse> add(StudentCourse request) {
        return Optional.empty();
    }

    @Override
    public Optional<StudentCourse> update(StudentCourse request) {
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        return true;
    }

    @Override
    public Optional<List<StudentCourse>> getByCourseId(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<StudentCourse>> getByStudentId(long id) {
        return Optional.empty();
    }
}
