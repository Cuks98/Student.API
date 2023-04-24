package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.commands.CourseCommand;
import hr.tvz.curin.studapp.domain.Course;
import hr.tvz.curin.studapp.dto.CourseDTO;
import hr.tvz.curin.studapp.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService{

    public CourseRepository courseRepository;
    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }
    @Override
    public List<CourseDTO> getAll() {
        try{
            return courseRepository.getAll().stream().map(this::mapCourseToCourseDTO).collect(Collectors.toList());
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public Optional<CourseDTO> getCourseById(long id) {
        Optional<Course> response = courseRepository.getCourseById(id);
        if(response.isPresent())
            return response.stream().map(this::mapCourseToCourseDTO).findFirst();
        return Optional.empty();
    }

    @Override
    public Optional<CourseDTO> add(CourseCommand request) {
        Optional<Course> response = courseRepository.add(mapCourseCommandToCourse(request));
        return response.map(this::mapCourseToCourseDTO);
    }

    @Override
    public Optional<CourseDTO> update(CourseCommand request) {
        return courseRepository.update(mapCourseCommandToCourse(request)).map(this::mapCourseToCourseDTO);
    }

    @Override
    public boolean delete(long id) {
        if(courseRepository.delete(id))
            return true;
        return false;
    }

    private CourseDTO mapCourseToCourseDTO(Course request){
        return new CourseDTO(request.name, request.ects);
    }

    private  Course mapCourseCommandToCourse(CourseCommand request){
        return new Course(request.id, request.name, request.ects);
    }
}
