package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.commands.StudentCourseCommand;
import hr.tvz.curin.studapp.domain.StudentCourse;
import hr.tvz.curin.studapp.dto.StudentCourseDTO;

import java.util.List;
import java.util.Optional;

public interface IStudentCourseService {
    Optional<List<StudentCourseDTO>> all() throws Exception;
    Optional<StudentCourseDTO> getById(long id) throws Exception;
    Optional<StudentCourseDTO> add(StudentCourseCommand request)throws Exception;
    Optional<StudentCourseDTO> update(StudentCourseCommand request) throws Exception;
    boolean delete(long id) throws Exception;
    Optional<List<StudentCourseDTO>> getByCourseId(long id) throws Exception;
    Optional<List<StudentCourseDTO>> getByStudentId(long id) throws Exception;
}
