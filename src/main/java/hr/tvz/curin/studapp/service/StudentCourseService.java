package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.commands.StudentCourseCommand;
import hr.tvz.curin.studapp.domain.StudentCourse;
import hr.tvz.curin.studapp.dto.StudentCourseDTO;
import hr.tvz.curin.studapp.repository.StudentCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentCourseService implements IStudentCourseService{

    public StudentCourseRepository studentCourseRepository;
    public StudentCourseService(StudentCourseRepository studentCourseRepository){
        this.studentCourseRepository = studentCourseRepository;
    }
    @Override
    public Optional<List<StudentCourseDTO>> all() throws Exception{
        try{
            Optional<List<StudentCourse>> response = studentCourseRepository.all();
            if(response.isPresent()){
                return Optional.of(response.get().stream()
                        .map(this::studentCourseToStudentCourseDtoMapper)
                        .collect(Collectors.toList())
                );
            }
            return Optional.empty();
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Optional<StudentCourseDTO> getById(long id) throws Exception{
        try{
            Optional<StudentCourse> studentCourse = studentCourseRepository.getById(id);
            if(studentCourse.isPresent()){
                return Optional.of(this.studentCourseToStudentCourseDtoMapper(studentCourse.get()));
            }
            return Optional.empty();
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Optional<StudentCourseDTO> add(StudentCourseCommand request) throws Exception {
        try{
            Optional<StudentCourse> response = studentCourseRepository.add(studentCourseCommandToStudentCourseMapper(request));
            if(response.isPresent()){
                return Optional.of(studentCourseToStudentCourseDtoMapper(response.get()));
            }
            return Optional.empty();
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Optional<StudentCourseDTO> update(StudentCourseCommand request) throws Exception{
        try{
            Optional<StudentCourse> response = studentCourseRepository.update(studentCourseCommandToStudentCourseMapper(request));
            if(response.isPresent()){
                return Optional.of(studentCourseToStudentCourseDtoMapper(response.get()));
            }
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) throws Exception {
        try{
            return studentCourseRepository.delete(id);
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Optional<List<StudentCourseDTO>> getByCourseId(long id) throws Exception {
        try{
            Optional<List<StudentCourse>> response = studentCourseRepository.getByCourseId(id);
            if(response.isPresent()){
                return Optional.of(response.get().stream()
                        .map(this::studentCourseToStudentCourseDtoMapper)
                        .collect(Collectors.toList()));
            }
            return Optional.empty();
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Optional<List<StudentCourseDTO>> getByStudentId(long id) throws Exception {
        try{
            Optional<List<StudentCourse>> response = studentCourseRepository.getByStudentId(id);
            if(response.isPresent()){
                return Optional.of(response.get().stream()
                        .map(this::studentCourseToStudentCourseDtoMapper)
                        .collect(Collectors.toList()));
            }
            return Optional.empty();
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }


    public StudentCourseDTO studentCourseToStudentCourseDtoMapper(StudentCourse request){
        return new StudentCourseDTO(request.studentId, request.courseId);
    }

    public StudentCourse studentCourseCommandToStudentCourseMapper(StudentCourseCommand command){
        return new StudentCourse(command.studentId, command.courseId);
    }
}
